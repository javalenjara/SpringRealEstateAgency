package co.com.udem.rea.rest.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.udem.rea.dto.IdTypeDTO;
import co.com.udem.rea.dto.parser.IdTypeParser;
import co.com.udem.rea.entity.IdType;
import co.com.udem.rea.repository.IdTypeRepository;

@RestController
public class IdTypeRestController {
	
	@Autowired
	private IdTypeRepository idTypeRepository;
	
	private IdTypeParser parsedIdType = new IdTypeParser();
	
	@PostMapping("/idType/addIdType")
	public ResponseEntity<String> addIdType(@RequestBody IdTypeDTO idTypeDTO) {
		
		try {
			IdType idType = parsedIdType.convertToEntity(idTypeDTO);
			idTypeRepository.save(idType);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok("IdType added. ID = " + idTypeDTO.getId());
	}
}