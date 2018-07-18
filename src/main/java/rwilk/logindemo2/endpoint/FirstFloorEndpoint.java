package rwilk.logindemo2.endpoint;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/floor1")
public class FirstFloorEndpoint {

  @GetMapping("office1")
  @PreAuthorize("hasAnyRole('USER')")
  public ResponseEntity<?> enterOffice1() {
    System.out.println(SecurityContextHolder.getContext().getAuthentication());

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("data", "You are inside office1");

    return new ResponseEntity<>(jsonObject, HttpStatus.OK);
  }

  @GetMapping("office2")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity enterOffice2() {
    return new ResponseEntity<>("You are inside office2", HttpStatus.OK);
  }
}
