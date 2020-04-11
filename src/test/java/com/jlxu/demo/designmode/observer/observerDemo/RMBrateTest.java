package com.jlxu.demo.designmode.observer.observerDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：人民币汇率”的升值或贬值对进口公司的进口产品成本或出口公司的出口产品收入以及公司的利润率的影
 * 创建时间：2020年04月10日
 * 文件名称：RMBrateTest
 * 版本：1.0.0
 * 最后修改时间：2020/4/10 23:17
 *
 * @auther jlxu
 */
public class RMBrateTest {
    public static void main(String[] args) {
        Rate rate = new RMBrate();
        Company ic = new ImportCompany();
        Company ec = new ExportCompany();
        rate.add(ic);
        rate.add(ec);
        rate.change(2);
        rate.change(-5);
        //人民币汇率升值2个基点，降低了进口产品成本，提升了进口公司利润率。
        //人民币汇率升值2个基点，降低了出口产品收入，降低了出口公司的销售利润率。
        //人民币汇率贬值5个基点，提升了进口产品成本，降低了进口公司利润率。
        //人民币汇率贬值5个基点，提升了出口产品收入，提升了出口公司的销售利润率。
    }
}


//抽象目标：汇率
abstract class Rate {
    protected List<Company> companies = new ArrayList<>();

    public void add(Company company) {
        companies.add(company);
    }

    public void remove(Company company) {
        companies.add(company);
    }

    public abstract void change(int number);
}

//具体目标：人民币汇率
class RMBrate extends Rate {

    @Override
    public void change(int number) {
        for (Company company : companies) {
            company.response(number);
        }
    }
}

//抽象观察者：公司
interface Company {
    void response(int number);
}

//具体观察者1：进口公司
class ImportCompany implements Company {

    @Override
    public void response(int number) {
        if (number > 0) {
            System.out.println("人民币汇率升值" + number + "个基点，降低了进口产品成本，提升了进口公司利润率。");
        } else if (number < 0) {
            System.out.println("人民币汇率贬值" + (-number) + "个基点，提升了进口产品成本，降低了进口公司利润率。");
        }
    }
}

//具体观察者2：出口公司
class ExportCompany implements Company {
    public void response(int number) {
        if (number > 0) {
            System.out.println("人民币汇率升值" + number + "个基点，降低了出口产品收入，降低了出口公司的销售利润率。");
        } else if (number < 0) {
            System.out.println("人民币汇率贬值" + (-number) + "个基点，提升了出口产品收入，提升了出口公司的销售利润率。");
        }
    }
}