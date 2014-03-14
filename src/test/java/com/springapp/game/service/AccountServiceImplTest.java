package com.springapp.game.service;

import com.springapp.game.dao.AccountDAO;
import com.springapp.game.model.Account;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by xya on 3/1/14.
 */
public class AccountServiceImplTest {

    @Test
    public void shouldChangePasswordWhenUsernameAndPasswordValid() throws Exception {
        //given
        AccountService accountService = new AccountService();
        Account account = new Account();
        String password = "123456";
        AccountDAO mockAccountDao = mock(AccountDAO.class);
        accountService.setAccountDAO(mockAccountDao);

        //when
        int result = accountService.changePassword(account, password);

        //then
        verify(mockAccountDao).changePassword(account);
        assertThat(result, is(1));

    }

    @Test
    public void shouldGetMatchCountReturnTrueIfUsernameAndPasswordMatch() throws Exception {
        //given
        AccountService accountService = new AccountService();
        AccountDAO mockAccountDao = mock(AccountDAO.class);
        accountService.setAccountDAO(mockAccountDao);
        String username = "";
        String password = "";
        when(mockAccountDao.getMatchCount(username, password)).thenReturn(1);

        //when
        int matchCount = mockAccountDao.getMatchCount(username, password);

        //then
        verify(mockAccountDao).getMatchCount(username, password);
        assertThat(matchCount, is(1));

    }
}
