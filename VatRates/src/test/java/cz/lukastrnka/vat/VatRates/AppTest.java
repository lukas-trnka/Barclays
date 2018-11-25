package cz.lukastrnka.vat.VatRates;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import cz.lukastrnka.vat.VatRates.controller.VatControllerTestNotParametrized;
import cz.lukastrnka.vat.VatRates.controller.VatControllerTestParametrized;
import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */

@RunWith(Suite.class)

@Suite.SuiteClasses({ VatControllerTestNotParametrized.class, VatControllerTestParametrized.class })

public class AppTest extends TestCase {

}
