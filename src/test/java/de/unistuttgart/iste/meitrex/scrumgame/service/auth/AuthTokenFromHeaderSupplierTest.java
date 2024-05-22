package de.unistuttgart.iste.meitrex.scrumgame.service.auth;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthTokenFromHeaderSupplierTest {

    @Test
    public void test_token_extraction_with_bearer_prefix() {
        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        ServletRequestAttributes attributes = mock(ServletRequestAttributes.class);
        when(attributes.getRequest()).thenReturn(request);
        when(request.getHeader("Authorization")).thenReturn("Bearer valid_token");
        RequestContextHolder.setRequestAttributes(attributes);
        AuthTokenFromHeaderSupplier supplier = new AuthTokenFromHeaderSupplier();

        // Act
        String token = supplier.get();

        // Assert
        assertThat(token, equalTo("valid_token"));
    }

    @Test
    public void test_token_returned_as_is_without_bearer_prefix() {
        // Arrange
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        ServletRequestAttributes attributes = Mockito.mock(ServletRequestAttributes.class);
        Mockito.when(attributes.getRequest()).thenReturn(request);
        Mockito.when(request.getHeader("Authorization")).thenReturn("valid_token");
        RequestContextHolder.setRequestAttributes(attributes);
        AuthTokenFromHeaderSupplier supplier = new AuthTokenFromHeaderSupplier();

        // Act
        String token = supplier.get();

        // Assert
        assertThat(token, equalTo("valid_token"));
    }

    @Test
    public void test_throws_illegal_state_exception_when_no_attributes() {
        // Arrange
        RequestContextHolder.setRequestAttributes(null);
        AuthTokenFromHeaderSupplier supplier = new AuthTokenFromHeaderSupplier();

        // Act and Assert
        assertThrows(IllegalStateException.class, supplier::get);
    }

    @Test
    public void test_throws_access_denied_exception_when_no_authorization_header() {
        // Arrange
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        ServletRequestAttributes attributes = Mockito.mock(ServletRequestAttributes.class);
        Mockito.when(attributes.getRequest()).thenReturn(request);
        Mockito.when(request.getHeader("Authorization")).thenReturn(null);
        RequestContextHolder.setRequestAttributes(attributes);
        AuthTokenFromHeaderSupplier supplier = new AuthTokenFromHeaderSupplier();

        // Act and Assert
        assertThrows(AccessDeniedException.class, supplier::get);
    }

}
