package java8.ex01;

import java8.data.Data;
import java8.data.Person;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Exercice 01 - Optional
 */
public class Optional_01_Test {

	class NotFoundException extends RuntimeException {

		/**
		 * 
		 */
		public NotFoundException() {
			super();
			// TODO Auto-generated constructor stub
		}

		/**
		 * @param message
		 * @param cause
		 * @param enableSuppression
		 * @param writableStackTrace
		 */
		public NotFoundException(String message, Throwable cause, boolean enableSuppression,
				boolean writableStackTrace) {
			super(message, cause, enableSuppression, writableStackTrace);
			// TODO Auto-generated constructor stub
		}

		/**
		 * @param message
		 * @param cause
		 */
		public NotFoundException(String message, Throwable cause) {
			super(message, cause);
			// TODO Auto-generated constructor stub
		}

		/**
		 * @param message
		 */
		public NotFoundException(String message) {
			super(message);
			// TODO Auto-generated constructor stub
		}

		/**
		 * @param cause
		 */
		public NotFoundException(Throwable cause) {
			super(cause);
			// TODO Auto-generated constructor stub
		}
	}

	// tag::findMethod[]
	<T> T find(List<T> list, Predicate<T> predicate) {
		T result = null;

		for (T p : list) {
			if (predicate.test(p)) {
				result = p;
				break;
			}
		}

		return result;
	}
	// end::findMethod[]

	@Test
	public void test_optional_found() throws Exception {

		List<Person> personList = Data.buildPersonList(100);

		// TODO invoquer la méthode find(List<T> list, Predicate<T> predicate)
		// TODO age == 10
		Optional<Person> result = Optional.of(find(personList, p -> p.getAge() == 10));

		assertThat(result, instanceOf(Optional.class));
		assertThat(result.isPresent(), is(true));
		assertThat(result.get(), instanceOf(Person.class));
		assertThat(result.get(), hasProperty("firstname", is("first_10")));
		assertThat(result.get(), hasProperty("age", is(10)));
	}

	@Test
	public void test_optional_notfound() throws Exception {

		List<Person> personList = Data.buildPersonList(100);

		// TODO invoquer la méthode find(List<T> list, Predicate<T> predicate)
		// TODO age == 400
		Optional<Person> result = Optional.ofNullable(find(personList, p -> p.getAge() == 400));

		assertThat(result, instanceOf(Optional.class));
		assertThat(result.isPresent(), is(false));
	}

	@Test(expected = NotFoundException.class)
	public void test_optional_notfound_throw_exception() throws Exception {

		List<Person> personList = Data.buildPersonList(100);

		// TODO invoquer la méthode find(List<T> list, Predicate<T> predicate)
		// TODO age == 10 et firstname == "last_10"
		Optional<Person> result = Optional
				.ofNullable(find(personList, p -> p.getAge() == 10 && p.getLastname().equals("last")));

		// TODO Utiliser la méthode orElseThrow pour déclencher l'exception
		// NotFoundException
		result.orElseThrow(() -> new NotFoundException("Aucune personne ne répond à ces critères"));
	}

	@Test
	public void test_optional_notfound_with_default_value() throws Exception {

		List<Person> personList = Data.buildPersonList(100);

		Person defaultValue = new Person();
		defaultValue.setFirstname("DEFAULT");
		defaultValue.setLastname("DEFAULT");

		// TODO invoquer la méthode find(List<T> list, Predicate<T> predicate, T
		// defaultValue)
		// TODO predicate => age == 400
		Person result = null;

		assertThat(result, notNullValue());
		assertThat(result, hasProperty("firstname", is("DEFAULT")));
		assertThat(result, hasProperty("lastname", is("DEFAULT")));
	}

}
