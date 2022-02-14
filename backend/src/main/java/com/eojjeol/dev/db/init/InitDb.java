package com.eojjeol.dev.db.init;

import com.eojjeol.dev.entity.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }
    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;
        private final PasswordEncoder passwordEncoder;


        public void dbInit1() {
            initAuthoriy();
        }

        private void initAuthoriy() {
            Authority userAuthority = new Authority();
            Authority userExist = em.find(Authority.class, "ROLE_USER");
            if(userExist == null) {
                userAuthority.setAuthorityName("ROLE_USER");
                em.persist(userAuthority);
            }

            Authority adminAuthority = new Authority();
            Authority adminExist = em.find(Authority.class, "ROLE_ADMIN");
            if(adminExist == null) {
                adminAuthority.setAuthorityName("ROLE_ADMIN");
                em.persist(adminAuthority);
            }

        }
    }
}

