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

// https://www.toptal.com/java/a-guide-to-everyday-mockito
// https://javadoc.io/static/org.mockito/mockito-core/4.1.0/org/mockito/Mockito.html#1
// https://developer.android.com/training/testing/unit-testing/local-unit-tests

@RunWith(MockitoJUnitRunner::class)
class DatabaseAdapterTest {
    @Mock
    lateinit var mockContext: Context
    
    @Mock
    lateinit var mockService: DatabaseService
//    var mockService: DatabaseService = mock(DatabaseService::class.java, Mockito.RETURNS_DEEP_STUBS )

    @Test
    fun testDatabaseAdapter() {
        // ** WITH context in the DatabaseService
        doReturn("DATABASE_TYPE").`when`(mockContext).getString(anyInt())
        // `when`(mockContext.getString(anyInt())).thenReturn("DATABASE_TYPE")  // alternate of above
        val service = DatabaseService(mockContext)
        val adapter = DatabaseAdapter(mockContext, service)

        // // ** WITHOUT using context in the DatabaseService
//        val adapter = DatabaseAdapter(mockContext, mockService) // can only use when "context" isn't needed in mockService
//        doReturn("DATABASE_TYPE").`when`(mockService).log(anyString()) // *** CANT DO THIS
//        `when`(mockService.log(anyString())).thenReturn(Unit) // *** CANT DO THIS

        val bo = ByteArrayOutputStream()
        System.setOut(PrintStream(bo)) // capture println from Logd/Logg.d (and log.d when overload in Log.kt)

        adapter.log("unit testing")

        bo.flush()
        val lines = String(bo.toByteArray()) // collect the lines that were output

        assert(lines.contains("DatabaseAdapter log:"))    // from DatabaseAdapter
        assert(lines.contains("DatabaseService type:"))   // from DatabaseService
        assert(lines.contains("DATABASE_TYPE"))           // from Context.getString(resId)
        assert(lines.contains("unit testing"))            // from this test
    }
}
