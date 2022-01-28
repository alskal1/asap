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
//        initService.dbInit2();
    }
    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;
//        private final MemberDataRepository memberDataRepository;
//        private final MemberService memberService;
        private final PasswordEncoder passwordEncoder;


        public void dbInit1() {

            initAuthoriy();


        }

        private void initAuthoriy() {
            Authority userAuthority = new Authority();
            userAuthority.setAuthorityName("ROLE_USER");

            Authority adminAuthority = new Authority();
            adminAuthority.setAuthorityName("ROLE_ADMIN");

            em.persist(userAuthority);
            em.persist(adminAuthority);
        }
    }
}

