package com.henli.data;

import java.util.List;

public class DrugDetail {
	 private int count;
	    private String description;
	    private int fcount;
	    private int id;
	    private String img;
	    private String keywords;
	    private String message;
	    private String name;
	    private int price;
	    private int rcount;
	    private boolean status;
	    private String tag;
	    private String type;
	    private String url;
	    /**
	     * code : 6927043102543
	     * drug : 1
	     * factory : ��������ҩҵ���޹�˾
	     * id : 15
	     */

	    private List<CodesEntity> codes;
	    /**
	     * drug : 1
	     * factory : �人����ҩҵ���޹�˾
	     * id : 8
	     * number : Z42020654
	     */

	    private List<NumbersEntity> numbers;

	    public void setCount(int count) {
	        this.count = count;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public void setFcount(int fcount) {
	        this.fcount = fcount;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public void setImg(String img) {
	        this.img = img;
	    }

	    public void setKeywords(String keywords) {
	        this.keywords = keywords;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public void setPrice(int price) {
	        this.price = price;
	    }

	    public void setRcount(int rcount) {
	        this.rcount = rcount;
	    }

	    public void setStatus(boolean status) {
	        this.status = status;
	    }

	    public void setTag(String tag) {
	        this.tag = tag;
	    }

	    public void setType(String type) {
	        this.type = type;
	    }

	    public void setUrl(String url) {
	        this.url = url;
	    }

	    public void setCodes(List<CodesEntity> codes) {
	        this.codes = codes;
	    }

	    public void setNumbers(List<NumbersEntity> numbers) {
	        this.numbers = numbers;
	    }

	    public int getCount() {
	        return count;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public int getFcount() {
	        return fcount;
	    }

	    public int getId() {
	        return id;
	    }

	    public String getImg() {
	        return img;
	    }

	    public String getKeywords() {
	        return keywords;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public String getName() {
	        return name;
	    }

	    public int getPrice() {
	        return price;
	    }

	    public int getRcount() {
	        return rcount;
	    }

	    public boolean isStatus() {
	        return status;
	    }

	    public String getTag() {
	        return tag;
	    }

	    public String getType() {
	        return type;
	    }

	    public String getUrl() {
	        return url;
	    }

	    public List<CodesEntity> getCodes() {
	        return codes;
	    }

	    public List<NumbersEntity> getNumbers() {
	        return numbers;
	    }

	    public static class CodesEntity {
	        private String code;
	        private int drug;
	        private String factory;
	        private int id;

	        public void setCode(String code) {
	            this.code = code;
	        }

	        public void setDrug(int drug) {
	            this.drug = drug;
	        }

	        public void setFactory(String factory) {
	            this.factory = factory;
	        }

	        public void setId(int id) {
	            this.id = id;
	        }

	        public String getCode() {
	            return code;
	        }

	        public int getDrug() {
	            return drug;
	        }

	        public String getFactory() {
	            return factory;
	        }

	        public int getId() {
	            return id;
	        }
	    }

	    public static class NumbersEntity {
	        private int drug;
	        private String factory;
	        private int id;
	        private String number;

	        public void setDrug(int drug) {
	            this.drug = drug;
	        }

	        public void setFactory(String factory) {
	            this.factory = factory;
	        }

	        public void setId(int id) {
	            this.id = id;
	        }

	        public void setNumber(String number) {
	            this.number = number;
	        }

	        public int getDrug() {
	            return drug;
	        }

	        public String getFactory() {
	            return factory;
	        }

	        public int getId() {
	            return id;
	        }

	        public String getNumber() {
	            return number;
	        }
	    }

}
