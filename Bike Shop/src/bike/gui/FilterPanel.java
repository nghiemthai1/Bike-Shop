package bike.gui;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.border.TitledBorder;

import bike.customerRecords.Customer;
import bike.inventory.Inventory;
import bike.inventory.Shop;
import bike.products.Accessory;
import bike.products.Bike;
import bike.products.Product;

public class FilterPanel extends JPanel implements ItemListener {

	private static final long serialVersionUID = 1L;

	public Toolkit windowParam = Toolkit.getDefaultToolkit();
	private Dimension minWindow = new Dimension(50,50);
	private LinkedHashMap<Product, Integer> accList = new LinkedHashMap<Product, Integer>();
	private Stream<Product> invFilter, bikeStream, typeStream, makeStream,modelStream,accessStream,genderStream,defaultStream;
	private Shop shop = new Shop();
	private ProductsDisplayPanel pnlDisplay;

	JPanel filter = new JPanel();
	JPanel choicePanel = new JPanel(new GridLayout(2,1));
	JPanel genderPanel = new JPanel(new GridLayout(1,1));
	JPanel typePanel = new JPanel();
	JPanel modelPanel = new JPanel();
	JPanel makePanel = new JPanel ();

	ButtonGroup modelGroup = new ButtonGroup();
	ButtonGroup makeGroup = new ButtonGroup();
	ButtonGroup typeGroup = new ButtonGroup();		
	HashSet<String> makeSet = new HashSet<String>();
	HashSet<String> modelSet = new HashSet<String>();
	HashSet<String> typeSet = new HashSet<String>();
	Set<Product> setStream = new HashSet<Product>();


	public FilterPanel(Inventory inventory, Customer customer, Frame frame, ProductsDisplayPanel pnlDisplay){

		shop.setInventory(inventory);

		this.accList = shop.getInventory().getProducts();
		setMinimumSize(minWindow);


		this.pnlDisplay = pnlDisplay;

		filter.setLayout(new BoxLayout(filter, BoxLayout.Y_AXIS));
		
		invFilter = inventory.filterInventory(product -> product instanceof Product  && inventory.inStock(product));

		setStream = invFilter.filter(product -> product instanceof Product).collect(Collectors.toSet());
		accessStream = setStream.stream();
		invFilter = inventory.filterInventory(product -> product instanceof Product  && inventory.inStock(product));


		//populate choice panel
		JCheckBox bikeBox = new JCheckBox("Bike");
		JCheckBox accessBox = new JCheckBox("Accessory");

		choicePanel.add(bikeBox);
		choicePanel.add(accessBox);

		//populate w/ bike info
		bikeBox.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e){
				if(bikeBox.isSelected()){
					accessBox.setSelected(false);
					typeSet.removeAll(typeSet);
					modelSet.removeAll(modelSet);
					makeSet.removeAll(makeSet);

					//Creates a stream of bikes from entire inventory
					setStream = invFilter.filter(product -> product instanceof Product).collect(Collectors.toSet());
					bikeStream = setStream.stream(); 
					invFilter = inventory.filterInventory(product -> product instanceof Product  && inventory.inStock(product));	

					Predicate<Product> isBike = product -> product instanceof Bike;
					Set<Product> bikeSet = accList.keySet().stream().filter(isBike).collect(Collectors.toSet());
					bikeStream = inventory.filterInventory(isBike);
					//invFilter.forEach(System.out::println);

					Iterator<Product> itType = bikeSet.iterator();
					pnlDisplay.removeAll();
					pnlDisplay.addProductsFromStream(bikeStream, customer, frame);
					revalidate();
					repaint();
					pnlDisplay.revalidate();
					pnlDisplay.repaint();

					JCheckBox genderBox = new JCheckBox("Woman");

					genderPanel.add(genderBox);
					setStream = inventory.filterInventory(isBike).collect(Collectors.toSet());
					genderStream = setStream.stream();
					invFilter = inventory.filterInventory(product -> product instanceof Product  && inventory.inStock(product));

					//populate w/ gender box
					genderBox.addItemListener(new ItemListener(){

						@Override
						public void itemStateChanged(ItemEvent k){

							pnlDisplay.removeAll();
							typeGroup.clearSelection();
							makeGroup.clearSelection();
							modelGroup.clearSelection();

							if(genderBox.isSelected()){

								setStream = inventory.filterInventory(isBike).collect(Collectors.toSet());
								genderStream = setStream.stream();
								invFilter = inventory.filterInventory(product -> product instanceof Product  && inventory.inStock(product));
								genderStream = genderStream.filter(product -> ((Bike) product).isWomens() );
								pnlDisplay.removeAll();
								pnlDisplay.addProductsFromStream(genderStream, customer, frame);
								revalidate();
								repaint();
								pnlDisplay.revalidate();
								pnlDisplay.repaint();
								typePanel.removeAll();
								modelPanel.removeAll();
								makePanel.removeAll();
							}
							else{
								setStream = inventory.filterInventory(isBike).collect(Collectors.toSet());
								genderStream = setStream.stream();
								invFilter = inventory.filterInventory(product -> product instanceof Product  && inventory.inStock(product));

								pnlDisplay.addProductsFromStream(genderStream, customer, frame);
								revalidate();
								repaint();
								pnlDisplay.revalidate();
								pnlDisplay.repaint();

								//populates hashSet of Types
								while(itType.hasNext())
								{
									Product next = itType.next();
									typeSet.add(next.getProductType());
								}


								//populates Panel w/ type checkboxes
								for(String type:typeSet){
									JCheckBox typeCheck = new JCheckBox(type);
									typeCheck.addItemListener(new ItemListener(){
										@Override
										public void itemStateChanged(ItemEvent z){

											if(((JCheckBox) z.getItem()).isSelected()){	

												setStream = invFilter.filter(product -> product instanceof Product).collect(Collectors.toSet());
												typeStream = setStream.stream();
												invFilter = inventory.filterInventory(product -> product instanceof Product  && inventory.inStock(product));
												typeStream = typeStream.filter(product -> product.getProductType().equals((((JCheckBox) z.getItem()).getText())));

												makeSet.removeAll(makeSet);
												modelSet.removeAll(modelSet);
												//modelPanel.removeAll();
												pnlDisplay.removeAll();
												pnlDisplay.addProductsFromStream(typeStream, customer, frame);
												revalidate();
												repaint();
												pnlDisplay.revalidate();
												pnlDisplay.repaint();	

												//invFilter.forEach(System.out::println);
												for(Product bike:bikeSet){
													if(bike.getProductType().equals(((JCheckBox) z.getItem()).getText())){
														makeSet.add(bike.getMake());
														makePanel.removeAll();					

														for(String make:makeSet){
															JCheckBox makeCheck = new JCheckBox(make);
															makePanel.add(makeCheck);
															makeGroup.add(makeCheck);
															makePanel.updateUI();
															//pnlDisplay.revalidate();
															//pnlDisplay.repaint();
															makeCheck.addItemListener(new ItemListener(){
																@Override
																public void itemStateChanged(ItemEvent f){
																	if(((JCheckBox) f.getItem()).isSelected()){
																		setStream = invFilter.filter(product -> product instanceof Product).collect(Collectors.toSet());
																		makeStream = setStream.stream().filter(product -> product.getProductType().equals((((JCheckBox) z.getItem()).getText())));;
																		invFilter = inventory.filterInventory(product -> product instanceof Product  && inventory.inStock(product));
																		makeStream = makeStream.filter(product -> product.getMake().equals((((JCheckBox) f.getItem()).getText())));
																		pnlDisplay.removeAll();
																		pnlDisplay.addProductsFromStream(makeStream, customer, frame);
																		
																		//modelPanel.removeAll();		
																		revalidate();
																		repaint();	
																		pnlDisplay.revalidate();
																		pnlDisplay.repaint();

																		for(Product bikeMod:bikeSet){
																			if(bikeMod.getMake().equals((((JCheckBox) f.getItem()).getText())) && bikeMod.getProductType().equals((((JCheckBox) z.getItem()).getText()))){
																				modelSet.add(bikeMod.getModel());
																				modelPanel.removeAll();


																				for(String model:modelSet){

																					JCheckBox modelCheck = new JCheckBox(model);
																					modelPanel.add(modelCheck);
																					modelGroup.add(modelCheck);
																					revalidate();
																					repaint();
																					pnlDisplay.revalidate();
																					pnlDisplay.repaint();
																					modelCheck.addItemListener(new ItemListener(){
																						@Override
																						public void itemStateChanged(ItemEvent g){
																							if(((JCheckBox) g.getItem()).isSelected()){
																								setStream = invFilter.filter(product -> product instanceof Product).collect(Collectors.toSet());
																								modelStream = setStream.stream().filter(product -> product.getMake().equals((((JCheckBox) f.getItem()).getText())));
																								invFilter = inventory.filterInventory(product -> product instanceof Product  && inventory.inStock(product));
																								modelStream = modelStream.filter(product -> product.getModel().equals((((JCheckBox) g.getItem()).getText())));
																								pnlDisplay.removeAll();
																								pnlDisplay.addProductsFromStream(modelStream, customer, frame);
																								revalidate();
																								repaint();
																								pnlDisplay.revalidate();
																								pnlDisplay.repaint();
																								//modelSet.forEach(System.out::println);
																							}

																						}

																					}
																					);
																				}
																			}
																		}
																	}

																}
															}
															);
														}				
													}						
												}
											}
										}
										}
									);
									typePanel.add(typeCheck);
									typeGroup.add(typeCheck);
								}
							}
						}
						}
					);
					setStream = inventory.filterInventory(isBike).collect(Collectors.toSet());
					genderStream = setStream.stream();
					invFilter = inventory.filterInventory(product -> product instanceof Product  && inventory.inStock(product));

					pnlDisplay.addProductsFromStream(genderStream, customer, frame);
					revalidate();
					repaint();
					pnlDisplay.revalidate();
					pnlDisplay.repaint();

					//populates hashSet of Types
					while(itType.hasNext())
					{
						Product next = itType.next();
						typeSet.add(next.getProductType());
					}

					//populates Panel w/ type checkboxes
					for(String type:typeSet){
						JCheckBox typeCheck = new JCheckBox(type);
						typeCheck.addItemListener(new ItemListener(){
							@Override
							public void itemStateChanged(ItemEvent z){

								if(((JCheckBox) z.getItem()).isSelected()){	

									setStream = invFilter.filter(product -> product instanceof Product).collect(Collectors.toSet());
									typeStream = setStream.stream();
									invFilter = inventory.filterInventory(product -> product instanceof Product  && inventory.inStock(product));
									typeStream = typeStream.filter(product -> product.getProductType().equals((((JCheckBox) z.getItem()).getText())));

									makeSet.removeAll(makeSet);
									modelSet.removeAll(modelSet);
									modelPanel.removeAll();
									pnlDisplay.removeAll();
									pnlDisplay.addProductsFromStream(typeStream, customer, frame);
									revalidate();
									repaint();
									pnlDisplay.revalidate();
									pnlDisplay.repaint();

									//invFilter.forEach(System.out::println);
									for(Product bike:bikeSet){
										if(bike.getProductType().equals(((JCheckBox) z.getItem()).getText())){
											makeSet.add(bike.getMake());
											makePanel.removeAll();					

											for(String make:makeSet){
												JCheckBox makeCheck = new JCheckBox(make);
												makePanel.add(makeCheck);
												makeGroup.add(makeCheck);
												revalidate();
												repaint();
												pnlDisplay.revalidate();
												pnlDisplay.repaint();
												makeCheck.addItemListener(new ItemListener(){
													@Override
													public void itemStateChanged(ItemEvent f){
														if(((JCheckBox) f.getItem()).isSelected()){
															setStream = invFilter.filter(product -> product instanceof Product).collect(Collectors.toSet());
															makeStream = setStream.stream().filter(product -> product.getProductType().equals((((JCheckBox) z.getItem()).getText())));;
															invFilter = inventory.filterInventory(product -> product instanceof Product  && inventory.inStock(product));
															makeStream = makeStream.filter(product -> product.getMake().equals((((JCheckBox) f.getItem()).getText())));

															pnlDisplay.removeAll();
															pnlDisplay.addProductsFromStream(makeStream, customer, frame);
															modelSet.removeAll(modelSet);
															modelPanel.removeAll();		
															revalidate();
															repaint();	
															pnlDisplay.revalidate();
															pnlDisplay.repaint();

															for(Product bikeMod:bikeSet){
																if(bikeMod.getMake().equals((((JCheckBox) f.getItem()).getText())) && bikeMod.getProductType().equals((((JCheckBox) z.getItem()).getText()))){
																	modelSet.add(bikeMod.getModel());
																	modelPanel.removeAll();



																	for(String model:modelSet){

																		JCheckBox modelCheck = new JCheckBox(model);
																		modelPanel.add(modelCheck);
																		modelGroup.add(modelCheck);
																		revalidate();
																		repaint();
																		pnlDisplay.revalidate();
																		pnlDisplay.repaint();
																		modelCheck.addItemListener(new ItemListener(){
																			@Override
																			public void itemStateChanged(ItemEvent g){
																				if(((JCheckBox) g.getItem()).isSelected()){
																					setStream = invFilter.filter(product -> product instanceof Product).collect(Collectors.toSet());
																					modelStream = setStream.stream().filter(product -> product.getMake().equals((((JCheckBox) f.getItem()).getText())));
																					invFilter = inventory.filterInventory(product -> product instanceof Product  && inventory.inStock(product));
																					modelStream = modelStream.filter(product -> product.getModel().equals((((JCheckBox) g.getItem()).getText())));
																					pnlDisplay.removeAll();
																					pnlDisplay.addProductsFromStream(modelStream, customer, frame);
																					revalidate();
																					repaint();
																					pnlDisplay.revalidate();
																					pnlDisplay.repaint();
																					//modelSet.forEach(System.out::println);
																				}

																			}

																		}
																		);
																	}
																}


															}
														}

													}
												}
												);
											}				
										}						
									}
								}
							}
							}
						);
						typePanel.add(typeCheck);
						typeGroup.add(typeCheck);
					}


					typePanel.setLayout(new GridLayout(typeSet.size(),1));
					makePanel.setLayout(new GridLayout(makeSet.size(),1));
					modelPanel.setLayout(new GridLayout(modelSet.size(),1));

				}


				else{
					pnlDisplay.removeAll();
					setStream = inventory.filterInventory(product -> product instanceof Product  && inventory.inStock(product)).collect(Collectors.toSet());
					defaultStream = setStream.stream();
					invFilter = inventory.filterInventory(product -> product instanceof Product  && inventory.inStock(product));
					pnlDisplay.addProductsFromStream(defaultStream, customer, frame);
					revalidate();
					repaint();
					pnlDisplay.revalidate();
					pnlDisplay.repaint();
					genderPanel.removeAll();
					typePanel.removeAll();
					modelPanel.removeAll();
					makePanel.removeAll();
				}
				revalidate();
				repaint();
			}
			}
		);



		//populate w/ accessory stuff


		accessBox.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e){
				if(accessBox.isSelected()){
					bikeBox.setSelected(false);

					typeSet.removeAll(typeSet);

					//Creates a stream of access from entire inventory
					setStream = invFilter.filter(product -> product instanceof Product).collect(Collectors.toSet());
					accessStream = setStream.stream(); 
					invFilter = inventory.filterInventory(product -> product instanceof Product  && inventory.inStock(product));	

					Predicate<Product> isaccess = product -> product instanceof Accessory;
					Set<Product> accessSet = accList.keySet().stream().filter(isaccess).collect(Collectors.toSet());
					accessStream = inventory.filterInventory(isaccess);
					//invFilter.forEach(System.out::println);

					Iterator<Product> itType = accessSet.iterator();
					pnlDisplay.removeAll();
					pnlDisplay.addProductsFromStream(accessStream, customer, frame);
					revalidate();
					repaint();
					pnlDisplay.revalidate();
					pnlDisplay.repaint();

					//populates hashSet of Types
					while(itType.hasNext())
					{
						Product next = itType.next();
						typeSet.add(next.getProductType());
					}


					//populates Panel w/ type checkboxes
					for(String type:typeSet){
						JCheckBox typeCheck = new JCheckBox(type);
						typeCheck.addItemListener(new ItemListener(){
							@Override
							public void itemStateChanged(ItemEvent z){

								if(((JCheckBox) z.getItem()).isSelected()){	

									setStream = invFilter.filter(product -> product instanceof Product).collect(Collectors.toSet());
									typeStream = setStream.stream();
									invFilter = inventory.filterInventory(product -> product instanceof Product  && inventory.inStock(product));
									typeStream = typeStream.filter(product -> product.getProductType().equals((((JCheckBox) z.getItem()).getText())));
									
									makeSet.removeAll(makeSet);
									modelSet.removeAll(modelSet);
									modelPanel.removeAll();
									pnlDisplay.removeAll();
									pnlDisplay.addProductsFromStream(typeStream, customer, frame);
									revalidate();
									repaint();
									pnlDisplay.revalidate();
									pnlDisplay.repaint();



									//invFilter.forEach(System.out::println);
									for(Product access:accessSet){
										if(access.getProductType().equals(((JCheckBox) z.getItem()).getText())){
											makeSet.add(access.getMake());
											makePanel.removeAll();					

											for(String make:makeSet){
												JCheckBox makeCheck = new JCheckBox(make);
												makePanel.add(makeCheck);
												makeGroup.add(makeCheck);
												revalidate();
												repaint();
												pnlDisplay.revalidate();
												pnlDisplay.repaint();
												makeCheck.addItemListener(new ItemListener(){
													@Override
													public void itemStateChanged(ItemEvent f){
														if(((JCheckBox) f.getItem()).isSelected()){
															setStream = invFilter.filter(product -> product instanceof Product).collect(Collectors.toSet());
															makeStream = setStream.stream().filter(product -> product.getProductType().equals((((JCheckBox) z.getItem()).getText())));;
															invFilter = inventory.filterInventory(product -> product instanceof Product  && inventory.inStock(product));
															makeStream = makeStream.filter(product -> product.getMake().equals((((JCheckBox) f.getItem()).getText())));

															pnlDisplay.removeAll();
															pnlDisplay.addProductsFromStream(makeStream, customer, frame);
															modelSet.removeAll(modelSet);
															modelPanel.removeAll();		
															revalidate();
															repaint();	
															pnlDisplay.revalidate();
															pnlDisplay.repaint();


															//SOME ERROR HERE. SHOWS ALL THE MODELS BASED ON THE MAKE AND DOESNT CARE ABOUT TYPE

															for(Product accessMod:accessSet){
																if(accessMod.getMake().equals((((JCheckBox) f.getItem()).getText())) && accessMod.getProductType().equals((((JCheckBox) z.getItem()).getText()))){
																	modelSet.add(accessMod.getModel());
																	modelPanel.removeAll();


																	for(String model:modelSet){

																		JCheckBox modelCheck = new JCheckBox(model); 
																		modelPanel.add(modelCheck);
																		modelGroup.add(modelCheck);
																		revalidate();
																		repaint();
																		pnlDisplay.revalidate();
																		pnlDisplay.repaint();
																		modelCheck.addItemListener(new ItemListener(){
																			@Override
																			public void itemStateChanged(ItemEvent g){
																				if(((JCheckBox) g.getItem()).isSelected()){
																					setStream = invFilter.filter(product -> product instanceof Product).collect(Collectors.toSet());
																					modelStream = setStream.stream().filter(product -> product.getMake().equals((((JCheckBox) f.getItem()).getText())));
																					invFilter = inventory.filterInventory(product -> product instanceof Product  && inventory.inStock(product));
																					modelStream = modelStream.filter(product -> product.getModel().equals((((JCheckBox) g.getItem()).getText())));
																					pnlDisplay.removeAll();
																					pnlDisplay.addProductsFromStream(modelStream, customer, frame);
																					revalidate();
																					repaint();
																					pnlDisplay.revalidate();
																					pnlDisplay.repaint();

																				}
																			}
																		}
																				);
																	}
																}
															}
														}
													}
												}
														);
											}				
										}						
									}
								}
							}
						}
								);
						typePanel.add(typeCheck);
						typeGroup.add(typeCheck);
					}


					typePanel.setLayout(new GridLayout(typeSet.size(),1));
					makePanel.setLayout(new GridLayout(makeSet.size(),1));
					modelPanel.setLayout(new GridLayout(modelSet.size(),1));
				}


				else{
					pnlDisplay.removeAll();
					setStream = inventory.filterInventory(product -> product instanceof Product  && inventory.inStock(product)).collect(Collectors.toSet());
					defaultStream = setStream.stream();
					invFilter = inventory.filterInventory(product -> product instanceof Product  && inventory.inStock(product));
					pnlDisplay.addProductsFromStream(defaultStream, customer, frame);
					revalidate();
					repaint();
					pnlDisplay.revalidate();
					pnlDisplay.repaint();
					genderPanel.removeAll();
					typePanel.removeAll();
					modelPanel.removeAll();
					makePanel.removeAll();
				}
				revalidate();
				repaint();
			}
			}
		);

		TitledBorder bikeTitle = BorderFactory.createTitledBorder("Choices:");
		choicePanel.setBorder(bikeTitle);
		TitledBorder genderTitle = BorderFactory.createTitledBorder("Gender:");
		genderPanel.setBorder(genderTitle);
		TitledBorder modelTitle = BorderFactory.createTitledBorder("Models:");
		modelPanel.setBorder(modelTitle);
		TitledBorder makeTitle = BorderFactory.createTitledBorder("Makes:");
		makePanel.setBorder(makeTitle);
		TitledBorder typeTitle = BorderFactory.createTitledBorder("Type:");
		typePanel.setBorder(typeTitle);

		filter.add(choicePanel);
		filter.add(genderPanel);
		filter.add(typePanel);
		filter.add(makePanel);
		filter.add(modelPanel);

		add(filter);
		revalidate();
		repaint();

	}

	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

	}

	public Stream<Product> getFilter(){
		return invFilter;
	}

}




