package java8.ex02;

import java8.data.Account;
import java8.data.Data;
import java8.data.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Exercice 02 - Map
 */
public class Lambda_02_Test {

	// tag::PersonToAccountMapper[]
	interface PersonToAccountMapper {
		Account map(Person p);
	}
	// end::PersonToAccountMapper[]

	// tag::PersonToFirstnameMapper[]
	interface PersonToFirstnameMapper {
		String map(Person p);
	}
	// end::PersonToFirstnameMapper[]

	/* pourrait être fait avec un générique List<T> et une interface PersonToSomething<T> */
	
	// tag::map[]
	private List<Account> mapAccounts(List<Person> personList, PersonToAccountMapper mapper) {
		List<Account> listeComptes = new ArrayList<>();
		for (Person person : personList)
			listeComptes.add(mapper.map(person));
		return listeComptes;
	}
	
	private List<String> mapFirstnames(List<Person> personList, PersonToFirstnameMapper mapper) {
		List<String> listePrenoms = new ArrayList<>();
		for (Person person : personList)
			listePrenoms.add(mapper.map(person));
		return listePrenoms;
	}
	// end::map[]

	// tag::test_map_person_to_account[]
	@Test
	public void test_map_person_to_account() throws Exception {

		List<Person> personList = Data.buildPersonList(100);

		// TODO transformer la liste de personnes en liste de comptes
		// TODO tous les objets comptes ont un solde à 100 par défaut
		List<Account> result = mapAccounts(personList, p -> new Account(p, 100));

		assert result.size() == personList.size();
		for (Account account : result) {
			assert account.getBalance().equals(100);
			assert account.getOwner() != null;
		}
	}
	// end::test_map_person_to_account[]

	// tag::test_map_person_to_firstname[]
	@Test
	public void test_map_person_to_firstname() throws Exception {

		List<Person> personList = Data.buildPersonList(100);

		// TODO transformer la liste de personnes en liste de prénoms
		List<String> result = mapFirstnames(personList, p -> p.getFirstname());

		assert result.size() == personList.size();
		for (String firstname : result) {
			assert firstname.startsWith("first");
		}
	}
	// end::test_map_person_to_firstname[]
}
