package com.aditya.tests.vendorportal.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record VendorPortalTestData(String username,
                                   String password,
                                   String monthlyEarning,
                                   String annualEarning,
                                   String profitMargin,
                                   String availableInventory,
                                   String searchKeyword,
                                   int searchResultsCount) {
}
