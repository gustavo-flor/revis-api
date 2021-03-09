package ogustaflor.com.github.revisapi.controller;

import lombok.RequiredArgsConstructor;
import ogustaflor.com.github.revisapi.entity.sheet.Sheet;
import ogustaflor.com.github.revisapi.entity.sheet.SheetDTO;
import ogustaflor.com.github.revisapi.service.SheetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sheets")
public class SheetController extends AbstractRestController<Sheet, SheetService, SheetDTO.Request.SheetStore, SheetDTO.Request.SheetUpdate> {
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/is-being-used/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> isBeingUsed(@PathVariable String title) {
		return ResponseEntity.ok(service.isBeingUsed(title));
	}
	
}
