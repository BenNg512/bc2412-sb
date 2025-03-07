package com.finance.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.finance.manager.model.QuoteDto;

@Service
public class FinanceManager {
CrumbManager crumbManager;
@Autowired
RestTemplate restTemplate;

  public String getUrl(String stockCode) {
    this.crumbManager = new CrumbManager();
    String url = "https://query1.finance.yahoo.com/v7/finance/quote?symbols="
                + stockCode + ".HK&crumb=" + crumbManager.getCrumb();
    return url;
  }
  public QuoteDto getApi(String stockCode) {
    this.crumbManager = new CrumbManager();
    String url = "https://query1.finance.yahoo.com/v7/finance/quote?symbols="
                + stockCode + ".HK&crumb=" + crumbManager.getCrumb();
    QuoteDto quoteDto = this.restTemplate.getForObject(url, QuoteDto.class);
    return quoteDto;
  }

  public static void main(String[] args) {
    CrumbManager crumbManager = new CrumbManager();
    String crumb = crumbManager.getCrumb();
    System.out.println("Fetched crumb: " + crumb);
    FinanceManager financeManager = new FinanceManager();
    String url = financeManager.getUrl("0005");
    System.out.println("Generated URL: " + url);
  }
  
}
