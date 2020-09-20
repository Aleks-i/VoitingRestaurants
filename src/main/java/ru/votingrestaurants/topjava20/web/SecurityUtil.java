package ru.votingrestaurants.topjava20.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.votingrestaurants.topjava20.AuthorizedAdmin;
import ru.votingrestaurants.topjava20.AuthorizedUser;

import static java.util.Objects.requireNonNull;

public class SecurityUtil {

    private SecurityUtil() {
    }

//      User
    public static AuthorizedUser safeGetUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof AuthorizedUser) ? (AuthorizedUser) principal : null;
    }

    public static AuthorizedUser getUser() {
        return requireNonNull(safeGetUser(), "No authorized user found");
    }

    public static int authUserId() {
        return getUser().getUserTo().id();
    }

//      Admin
    public static AuthorizedAdmin safeGetAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof AuthorizedAdmin) ? (AuthorizedAdmin) principal : null;
    }

    public static AuthorizedAdmin getAdmin() {
        return requireNonNull(safeGetAdmin(), "No authorized user found");
    }

    public static int authAdminId() {
        return getAdmin().getAdminTo().id();
    }
}
