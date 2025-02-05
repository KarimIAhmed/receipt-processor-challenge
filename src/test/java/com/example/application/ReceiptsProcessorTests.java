package com.example.application;

import com.example.application.model.Item;
import com.example.application.model.Receipt;
import com.example.application.repository.ReceiptRepository;
import com.example.application.service.ReceiptService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ReceiptsProcessorTests {
    private ReceiptService receiptService;
	private ReceiptRepository receiptRepository;
	@BeforeEach
	void setUp(){
		receiptRepository= new ReceiptRepository();
		receiptService=new ReceiptService(receiptRepository);
	}
	@Test
	void contextLoads() {

	}

	@Test
	void testProcess_ExpectedSuccess(){
		Receipt receipt=new Receipt("name","01-01-2021","12:00",new Item[]{},"4.00");

		String id=receiptService.process(receipt);

		assert(id != null);
	}

	@Test
	void testGetPointsById_ExpectedSuccess(){
		String id="4d5f62e6-e7a8";
		Receipt receipt=new Receipt("Target","2022-01-01","13:01",
				new Item[]{new Item("Mountain Dew 12PK","6.49"),
				new Item("Emils Cheese Pizza","12.25"),
				new Item("Knorr Creamy Chicken","1.26"),
				new Item("Doritos Nacho Cheese","3.35"),
				new Item("   Klarbrunn 12-PK 12 FL OZ  ","12.00")},"35.35");

		receiptRepository.insertIntoDatabase(id,receipt);
		assertEquals(28, receiptService.getPointsById(id));
	}

	@Test
	void testGetPointsById_ExpectedSuccess2(){
		String id="4d5f62e6-e7a8";
		Receipt receipt=new Receipt("M&M Corner Market","2022-03-20","14:33",
				new Item[]{new Item("Gatorade","2.25"),
						new Item("Gatorade","2.25"),
						new Item("Gatorade","2.25"),
						new Item("Gatorade","2.25")},"9.00");

		receiptRepository.insertIntoDatabase(id,receipt);
		assertEquals(109, receiptService.getPointsById(id));
	}

	@Test
	void testGetPointsById_ExpectedFailure(){
		assertEquals(-1, receiptService.getPointsById("fakeid"));
	}

}
