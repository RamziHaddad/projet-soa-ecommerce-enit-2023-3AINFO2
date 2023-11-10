package Java.enit.Catalog.dto;

import Java.enit.Catalog.domain.Catalog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatalogRequest {
	private String name;
	private String description;
}
