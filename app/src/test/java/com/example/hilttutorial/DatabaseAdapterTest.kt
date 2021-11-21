package com.example.hilttutorial

import android.content.Context
import com.example.hilttutorial.database.DatabaseAdapter
import com.example.hilttutorial.database.DatabaseService
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Answers
import org.mockito.ArgumentMatchers.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.io.ByteArrayOutputStream
import java.io.PrintStream

@RunWith(MockitoJUnitRunner::class)
class DatabaseAdapterTest {
    @Mock
    lateinit var mockContext: Context
    
    @Mock
    lateinit var mockService: DatabaseService

    //var mockService = mock(DatabaseService::class.java, Mockito.RETURNS_DEEP_STUBS )

    @Test
    fun testDatabaseAdapter() {
        doReturn("DATABASE_TYPE").`when`(mockContext).getString(anyInt())
        val service = DatabaseService(mockContext)
        val adapter = DatabaseAdapter(mockContext, service)

//        val adapter = DatabaseAdapter(mockContext, service) // can only use if "context" isn't needed. Cant seem to mock
//        doReturn("DATABASE_TYPE").`when`(adapter.databaseService).log(anyString())
//        `when`(mockService.log(anyString())).thenReturn(Unit)

        val bo = ByteArrayOutputStream()
        System.setOut(PrintStream(bo)) // capture println from Logd/Logg.d/log.d

        adapter.log("unit testing")

        bo.flush()
        val lines = String(bo.toByteArray())

        assert(lines.contains("DatabaseAdapter log:"))
        assert(lines.contains("DatabaseService type:"))
        assert(lines.contains("DATABASE_TYPE"))
        assert(lines.contains("unit testing"))
    }
}
