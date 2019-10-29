package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.items.TestBasket;
import test.items.TestPacket;
import test.items.TestStation;

@RunWith(Suite.class)
@SuiteClasses({ TestStation.class, 
				TestBasket.class,
				TestPacket.class})
public class JUnitTestSuite {

}
