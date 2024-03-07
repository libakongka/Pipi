package com.example.oauthsession.dto;

public enum ProductTag {
	
	A("심플베이직"),
	B("캐주얼"),
	C("모던시크"),
	D("러블리"),
	E("로맨틱"),
	F("유니크"),
	G("빈티지"),
	H("오피스룩"),
	I("캠퍼스룩"),
	J("아메카지"),
	K("섹시글램"),
	L("럭셔리"),
	M("컨템포러리"),
	N("클래식"),
	O("유니섹스");
//	P("시그니처제품"),
//	Q("유성마카"),
//	R("유성펜"),
//	S("중간굵기필기용"),
//	T("진하고선명한색상"),
//	U("카트리지형리필방식"),
//	V("컨버터리필방식"),
//	W("컬러링용"),
//	X("트윈타입"),
//	Y("프리미엄펜");
	
	private final String label;
	
	ProductTag(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}
