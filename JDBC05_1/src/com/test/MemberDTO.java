/*===================
   MemberDTO.java   
===================*/
// JDBC05_1


package com.test;

public class MemberDTO
{
	private int id, basicpay, sudang, pay;
	private String name, ssn, ibsadate, tel, city, buseo, jikwi;
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getBasicpay()
	{
		return basicpay;
	}
	public void setBasicpay(int basicpay)
	{
		this.basicpay = basicpay;
	}
	public int getSudang()
	{
		return sudang;
	}
	public void setSudang(int sudang)
	{
		this.sudang = sudang;
	}
	public int getPay()
	{
		return pay;
	}
	public void setPay(int pay)
	{
		this.pay = pay;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getSsn()
	{
		return ssn;
	}
	public void setSsn(String ssn)
	{
		this.ssn = ssn;
	}
	public String getIbsadate()
	{
		return ibsadate;
	}
	public void setIbsadate(String ibsadate)
	{
		this.ibsadate = ibsadate;
	}
	public String getTel()
	{
		return tel;
	}
	public void setTel(String tel)
	{
		this.tel = tel;
	}
	public String getCity()
	{
		return city;
	}
	public void setCity(String city)
	{
		this.city = city;
	}
	public String getBuseo()
	{
		return buseo;
	}
	public void setBuseo(String buseo)
	{
		this.buseo = buseo;
	}
	public String getJikwi()
	{
		return jikwi;
	}
	public void setJikwi(String jikwi)
	{
		this.jikwi = jikwi;
	}
}
