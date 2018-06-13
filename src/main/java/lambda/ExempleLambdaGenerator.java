package lambda;

public class ExempleLambdaGenerator {

	public static void main(String[] args) {
		Generator g1 = new Generator() {

			@Override
			public String getValue() {
				return "wesh alors";
			}
		};

		Generator g2 = () -> {
			return "wesh alors";
		};

		Generator g3 = () -> "wesh alors";
	}

}
