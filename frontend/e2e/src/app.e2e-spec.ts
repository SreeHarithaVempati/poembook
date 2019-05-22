import { AppPage } from './app.po';
import { browser, element, by, protractor } from 'protractor';

describe('poem App', async() => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
    browser.manage().window().maximize();

  });

  it('should be redirected to login page on loading application', async() => {
    page.navigateTo();
    expect(browser.getCurrentUrl()).toContain('login');
  });

  it('should be redirected to register', async() => {
    browser.element(by.css('#register-link')).click();
    expect(browser.getCurrentUrl()).toContain('register');
  });

  it('should register user', async() => {
    browser.element(by.id('firstName')).sendKeys('harry');
    browser.element(by.id('lastName')).sendKeys('potter');
    browser.element(by.id('userId')).sendKeys('hry');
    browser.element(by.id('password')).sendKeys('potter');
    browser.element(by.id('register-user')).click();
	browser.sleep(2000);
    expect(browser.getCurrentUrl()).toContain('login');
  });

  it('should be able to login user and navigate to dashboard', async() => {
    browser.driver.manage().window().maximize();
    browser.element(by.id('userid')).sendKeys('ayushi');
    browser.element(by.id('password')).sendKeys('123456');
    browser.element(by.id('loginbtn1')).click();
    browser.sleep(5000);
    expect(browser.getCurrentUrl()).toContain('dashboard');
  });

  it('should be able to search poems', async() => {
    browser.element(by.id('searchForm')).sendKeys('Sonnet 2: When forty winters shall besiege thy brow');
    browser.element(by.id('searchbtn')).sendKeys(protractor.Key.ENTER);
    browser.sleep(2000);
    const searchItems = element.all(by.id('cardpoem'));
    expect(searchItems.isPresent()).toBeTruthy();
  });

  it('should be able to add poem to favorite', async() => {
    browser.driver.sleep(1000);
    browser.element(by.css('.addfab')).click();
    browser.driver.sleep(10000);
    browser.element(by.css('#closemessage')).click();
    browser.driver.sleep(10000);
  });
  it('should be able to navigate to favorites page',async()=>{
    browser.element(by.css('.favoritePage')).click();
browser.sleep(5000);
    expect(browser.getCurrentUrl()).toContain('favourites');
  })
  it('should be able to delete from favorites page', async() => {
    browser.driver.sleep(1000);
    const searchItems = element.all(by.id('favouritecard'));
    searchItems.get(0).click();
    browser.element(by.css('.delete')).click();
    browser.driver.sleep(10000);
  });
it('should be able to logout and go back to login page', ()=>{
  browser.element(by.css('.logout')).click();
  browser.driver.sleep(2000);
  expect(browser.getCurrentUrl()).toContain('/');
  
  });


});
