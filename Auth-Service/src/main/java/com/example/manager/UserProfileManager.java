package com.example.manager;

import com.example.dto.request.UserSaveRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.example.constant.RestApiUrls.*;

/**
 *
 * Microservisler arası iletişimi RestAPI üzerinden sağlamak için kullanılır
 * İletişim kurulacak servisin controller katmanına istek atar
 * iki adet parametresini özellikle kullanmalıyız:
 * 1- url: istek atılacak controller sınıfına erişim adresine yazıyoruz
 * 2- name (optional) : yazılan her bir manager için bir isim veriyoruz zorunlu değildir ancak
 * aynı ismi taşıyan birden fazla manager olursa sistem hata verir. Sorunu anlamanız mümkün olmaya bilir.
 * Kullanırken dikkatli olun.
 */
@FeignClient(url = "http://localhost:9094/dev/v1/user", name = "userProfileManager")
public interface UserProfileManager {
    @PostMapping(ADD)
    public ResponseEntity<Void> save(@RequestBody UserSaveRequestDto dto);

}

