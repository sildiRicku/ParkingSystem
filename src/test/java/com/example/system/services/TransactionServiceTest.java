package com.example.system.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.example.system.models.Transaction;
import com.example.system.repositories.TransactionRepo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepo transactionRepo;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveTransaction() {
        Transaction inputTransaction = Mockito.mock(Transaction.class);
        Transaction savedTransaction = Mockito.mock(Transaction.class);

        when(transactionService.saveTransaction(inputTransaction)).thenReturn(savedTransaction);

        Transaction result = transactionService.saveTransaction(inputTransaction);

        assertEquals(savedTransaction, result);

        verify(transactionRepo, times(1)).save(inputTransaction);
    }


}
