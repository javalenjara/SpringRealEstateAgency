package co.com.udem.rea.rest.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.udem.rea.dto.EstateDTO;
import co.com.udem.rea.entity.Estate;
import co.com.udem.rea.repository.EstateRepository;
import co.com.udem.rea.util.Constants;
import co.com.udem.rea.util.EstateParser;
//import co.com.udem.rea.util.UserParser;

@RestController
public class EstateRestController {
	
	@Autowired
	private EstateRepository estateRepository;
	
	@Autowired
	private EstateParser convertEstate;
	
//	@Autowired
//	private UserParser convertUser;
	
	@PostMapping("/estates/addEstate")
	public Map<String, String> addEstate(@RequestBody EstateDTO estateDTO) {
		
		Map<String, String> response = new HashMap<>();
		
		try {
			Estate estate = convertEstate.convertToEntity(estateDTO);
			estateRepository.save(estate);
			response.put(Constants.HTTP_CODE, "200");
			response.put(Constants.SUCCESS_MESSAGE, "1 row added successfuly");
			return response;
		} catch (ParseException e) {
			response.put(Constants.HTTP_CODE, "500");
			response.put(Constants.ERROR_MESSAGE, "A problem occurred while trying to insert a row");
			return response;
		}
	}
	
//	@GetMapping("/estates")
//    public Set<EstateDTO> getEstates(){
//        Iterable<Estate> iEstates = estateRepository.findAll();
//        Set<Estate> estates = new HashSet<>();
//        Set<EstateDTO> estatesDTO = new HashSet<>();
//        iEstates.iterator().forEachRemaining(estates::add);
//        estates.stream().forEach(x -> x = convertEstate.convertToDTO(x));
//        for(int i = 0; i < estates.size(); i++) {
//            try {
//            	EstateDTO estateDTO = null;
//                if(estates..get(i).getDirectorTecnico() != null) {
//                    directorTecnicoDTO = convertUser.convertToDTO(estates.get(i).getDirectorTecnico());
//                }
//                ClubFutbolDTO clubFutbolDTO = convertEstate.convertToDTO(estates.get(i));
//                clubFutbolDTO.setDirectorTecnicoDTO(directorTecnicoDTO);
//                estatesDTO.add(clubFutbolDTO);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
//       
//        return estatesDTO;
//    }

}
