package com.revature.hai_app.services;

import com.revature.hai_app.daos.userDAO;
import com.revature.hai_app.util.custom_exceptions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    UserService userService = new UserService(new userDAO());

    @Test
    void isValidUsername_WillThrowExceptionIfUsernameHasSymbols() {
        /* AAA = Act, Arrange, Assert */

        /* Act */
        String username = "h@ibun";

        /* Arrange */

        /* Assert */
        assertThrows(InvalidUserException.class, () -> userService.isValidUsername(username));
    }

    @Test
    void isValidUsername_WillThrowExceptionUsernameIsEmpty() {
        /* AAA = Act, Arrange, Assert */

        /* Act */
        String username = " ";

        /* Arrange */

        /* Assert */
        assertThrows(InvalidUserException.class, () -> userService.isValidUsername(username));
    }

    @Test
    void isValidUsername_WillThrowExceptionUsernameIsLongerThan20Chars() {
        /* AAA = Act, Arrange, Assert */

        /* Act */
        String username = "wabalabadoodoomiesinthehouse";

        /* Arrange */

        /* Assert */
        assertThrows(InvalidUserException.class, () -> userService.isValidUsername(username));
    }
    @Test
    void isValidPassWord_WillThrowExceptionIfPwHasNoSymbol() {
        /* AAA = Act, Arrange, Assert */

        /* Act */
        String password = "Yuuji123123";

        /* Arrange */

        /* Assert */
        assertThrows(InvalidPasswordException.class, () -> userService.isValidPassWord(password));
    }
    @Test
    void isValidPassWord_WillThrowExceptionIfPwIsEmpty() {
        /* AAA = Act, Arrange, Assert */

        /* Act */
        String password = " ";

        /* Arrange */

        /* Assert */
        assertThrows(InvalidPasswordException.class, () -> userService.isValidPassWord(password));
    }

    @Test
    void isValidPassWord_WillThrowExceptionIfPwHasNoNumbers() {
        /* AAA = Act, Arrange, Assert */

        /* Act */
        String password = "reallylongp@ssword";

        /* Arrange */

        /* Assert */
        assertThrows(InvalidPasswordException.class, () -> userService.isValidPassWord(password));
    }
    @Test
    void isValidEmail_WillThrowExceptionIfEmailIsEmpty() {
        /* AAA = Act, Arrange, Assert */

        /* Act */
        String email = " ";

        /* Arrange */

        /* Assert */
        assertThrows(InvalidEmailException.class, () -> userService.isValidEmail(email));
    }

    @Test
    void isValidEmail_WillThrowExceptionIfEmailHasNoAtSymbol() {
        /* AAA = Act, Arrange, Assert */

        /* Act */
        String email = "haiisreallycool.com";

        /* Arrange */

        /* Assert */
        assertThrows(InvalidEmailException.class, () -> userService.isValidEmail(email));
    }


    @Test
    void isValidAddress_WillThrowExceptionIfAddressIsInvalidFormat() {
        /* AAA = Act, Arrange, Assert */

        /* Act */
        String address = "1111 some";

        /* Arrange */

        /* Assert */
        assertThrows(InvalidAddressException.class, () -> userService.isValidAddress(address));
    }

    @Test
    void isValidAddress_WillThrowExceptionIfAddressIsEmpty() {
        /* AAA = Act, Arrange, Assert */

        /* Act */
        String address = " ";

        /* Arrange */

        /* Assert */
        assertThrows(InvalidAddressException.class, () -> userService.isValidAddress(address));
    }

    @Test
    void isNotDuplicateEmail_WillThrowExceptionIfEmailAlreadyExists() {
        /* AAA = Act, Arrange, Assert */

        /* Act */
        String email = "admin@gmail.com";

        /* Arrange */

        /* Assert */
        assertThrows(InvalidEmailException.class, () -> userService.isNotDuplicateEmail(email));
    }

    @Test
    void isNotDuplicateUsername_WillThrowExceptionIfEmailAlreadyExists() {
        /* AAA = Act, Arrange, Assert */

        /* Act */
        String userName = "admin";

        /* Arrange */

        /* Assert */
        assertThrows(InvalidUserException.class, () -> userService.isNotDuplicateUsername(userName));
    }


    @Test
    void isValidState_WillThrowExceptionIfStateIsIncorrect() {
        /* AAA = Act, Arrange, Assert */

        /* Act */
        String state = "AG";

        /* Arrange */

        /* Assert */
        assertThrows(InvalidStateException.class, () -> userService.isValidState(state));
    }
}