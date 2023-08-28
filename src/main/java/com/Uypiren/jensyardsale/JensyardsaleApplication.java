package com.Uypiren.jensyardsale;

import com.Uypiren.jensyardsale.model.selections.DropDownSelection;
import com.Uypiren.jensyardsale.repository.DropDownSelectionRepository;
import com.Uypiren.jensyardsale.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JensyardsaleApplication implements CommandLineRunner {

	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private DropDownSelectionRepository selectionRepository;

	public static void main(String[] args) {
		SpringApplication.run(JensyardsaleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		DropDownSelection selection  = new DropDownSelection();
		selection.setSelectionType(100);
		selection.setSelectionValue("On Hold");
	//	selectionRepository.save(selection);


		DropDownSelection selection1  = new DropDownSelection();
		selection1.setSelectionType(100);
		selection1.setSelectionValue("For Sale");
	//	selectionRepository.save(selection1);

		DropDownSelection selection2  = new DropDownSelection();
		selection2.setSelectionType(200);
		selection2.setSelectionValue("Like New");
	//	selectionRepository.save(selection2);

		DropDownSelection selection3  = new DropDownSelection();
		selection3.setSelectionType(200);
		selection3.setSelectionValue("Used In Working Condition");
	//	selectionRepository.save(selection3);

		DropDownSelection selection4  = new DropDownSelection();
		selection4.setSelectionType(200);
		selection4.setSelectionValue("Broken");
		//selectionRepository.save(selection4);


		DropDownSelection selection5  = new DropDownSelection();
		selection5.setSelectionType(300);
		selection5.setSelectionValue("Clothing");
	//	selectionRepository.save(selection5);

		DropDownSelection selection6  = new DropDownSelection();
		selection6.setSelectionType(300);
		selection6.setSelectionValue("Jewelery");
	//	selectionRepository.save(selection6);

	}
}
