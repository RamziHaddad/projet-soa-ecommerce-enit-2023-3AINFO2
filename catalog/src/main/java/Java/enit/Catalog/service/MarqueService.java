package Java.enit.Catalog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import Java.enit.Catalog.domain.Marque;
import Java.enit.Catalog.dto.MarqueRequest;
import Java.enit.Catalog.dto.MarqueResponse;
import Java.enit.Catalog.infrastructure.MarqueRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MarqueService {

    private final MarqueRepository marqueRepository;

    public Marque createMarque(MarqueRequest marqueRequest) {
        Marque marque = Marque.builder()
                .name(marqueRequest.getName())
                .description(marqueRequest.getDescription())
                .build();
        return marqueRepository.save(marque);
    }

    public List<MarqueResponse> getAllMarques() {
        List<Marque> marques = marqueRepository.findAll();
        return marques.stream().map(this::maptoMarqueResponse).toList();
    }

    public Page<MarqueResponse> findAll(Pageable pageable) {
        return marqueRepository.findAll(pageable).map(this::maptoMarqueResponse);
    }

    public Optional<Marque> getMarqueById(Long id) {
        return marqueRepository.findById(id);
    }

    public Marque updateMarque(Long id, Marque updatedMarque) {
        Optional<Marque> existingMarque = marqueRepository.findById(id);
        if (existingMarque.isPresent()) {
            Marque marque = existingMarque.get();
            marque.setName(updatedMarque.getName());
            marque.setDescription(updatedMarque.getDescription());
            return marqueRepository.save(marque);
        } else {
            return null;
        }
    }

    public void deleteMarque(Long id) {
        marqueRepository.deleteById(id);
    }

    private MarqueResponse maptoMarqueResponse(Marque marque) {
        return MarqueResponse.builder()
                .id(marque.getId())
                .name(marque.getName())
                .description(marque.getDescription())
                .build();
    }
}
