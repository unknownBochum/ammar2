package fabrik;

public class ConcreteCreatorA extends Creator{

	@Override
	public Product factoryMethod() {
		// TODO Auto-generated method stub
		return new ConcreteProductA();
	}

}
