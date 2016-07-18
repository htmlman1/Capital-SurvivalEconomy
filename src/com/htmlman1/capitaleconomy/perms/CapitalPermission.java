package com.htmlman1.capitaleconomy.perms;

public enum CapitalPermission {
	
	BANK_DEPOSIT("capital.bank.deposit"),
	BANK_WITHDRAW("capital.bank.withdraw"),
	BUY_LOTTERY("capital.lotto.buy"),
	CHECK_ALL_CASH("capital.bal.others.cash"),
	CHECK_ALL_DEBIT("capital.bal.others.debit"),
	CHECK_ALL_VAULT("capital.bal.others.vault"),
	CHECK_THEIR_CASH("capital.bal.cash"),
	CHECK_THEIR_DEBIT("capital.bal.debit"),
	CHECK_THEIR_VAULT("capital.bal.vault"),
	CHECK_SERVER_DEBIT("capital.bal.server.debit"),
	CHECK_SERVER_VAULT("capital.bal.server.vault"),
	CHECK_LOTTERY("capital.lotto.bal"),
	GIVE_MONEY("capital.give"),
	PAY_OTHERS("capital.pay"),
	SET_PAY_PREF("capital.shop.createcapital.pref"),
	SHOW_TOP_DEBIT("capital.bal.top"),
	TAKE_MONEY("capital.take"),
	USE_SHOP("capital.shop.use");
	
	private String permission;
	
	CapitalPermission(String permission) {
		this.permission = permission;
	}
	
	public String getApplicablePermission() {
		return this.permission;
	}
	
}
