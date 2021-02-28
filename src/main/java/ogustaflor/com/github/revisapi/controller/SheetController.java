package ogustaflor.com.github.revisapi.controller;

import lombok.RequiredArgsConstructor;
import ogustaflor.com.github.revisapi.entity.sheet.Sheet;
import ogustaflor.com.github.revisapi.entity.sheet.SheetDTO;
import ogustaflor.com.github.revisapi.service.SheetService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sheets")
public class SheetController {
	
	private final SheetService sheetService;
	
	@GetMapping
	public ResponseEntity<List<Sheet>> index() {
		return ResponseEntity.ok(sheetService.findAll());
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<Sheet>> pageable(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
												@RequestParam(name = "size", required = false, defaultValue = "8") int size) {
		return ResponseEntity.ok(sheetService.paginate(PageRequest.of(page, size)));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Sheet> show(@PathVariable Long id) {
		return sheetService.findById(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.noContent().build());
	}
	
	@PostMapping
	public ResponseEntity<Sheet> store(@Valid @RequestBody SheetDTO.Request.Store body) throws Exception {
		Sheet createdSheet = sheetService.insert(body.toEntity());
		URI location = URI.create(String.format("/sheets/%s", createdSheet.getId()));
		return ResponseEntity.created(location).body(createdSheet);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Sheet> update(@Valid @RequestBody SheetDTO.Request.Update body, @PathVariable Long id) throws Exception {
		return ResponseEntity.ok(sheetService.update(id, body.toEntity()));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> destroy(@PathVariable Long id) {
		sheetService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/is-being-used/{title}")
	public ResponseEntity<Boolean> isBeingUsed(@PathVariable String title) {
		return ResponseEntity.ok(sheetService.isBeingUsed(title));
	}
	
}
