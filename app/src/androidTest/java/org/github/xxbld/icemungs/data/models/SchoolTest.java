package org.github.xxbld.icemungs.data.models;


import android.test.AndroidTestCase;

import org.github.xxbld.icemung.utils.MLog;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobGeoPoint;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by xxbld on 2016/5/5.
 * you can contact me at: 1024920618@qq.com
 *
 * @description :
 */
public class SchoolTest extends AndroidTestCase {

    private static final String TAG = SchoolTest.class.getSimpleName();

    //       Jxust Id e40489eba1
//        JCAcademy Id 0e390593ab
    public void registerSchool() {
        School mSchool = new School();
        mSchool.setSchoolName("江西理工大学");
        mSchool.setSchoolIntroduction(getJxustIntroduction());
        mSchool.setSchoolLocation(new BmobGeoPoint(114.929473, 25.854084));
        mSchool.setSchoolAddress("江西省赣州市红旗大道86号");
        mSchool.setSchoolPhoneNumber("0797-8312161");
        mSchool.setSchoolEmail(null);
        mSchool.setSchoolLogoImUrl(null);
        BmobRelation relation = new BmobRelation();
        relation.add(getAcademiesRelation(mSchool));
        mSchool.setAcademies(relation);
        mSchool.save(getContext(), new SaveListener() {
            @Override
            public void onSuccess() {
                MLog.i(TAG, "Save Success!!");
            }

            @Override
            public void onFailure(int i, String s) {
                MLog.i(TAG, "Save Failed!!");
            }
        });
    }

    private String getJxustIntroduction() {
        StringBuilder builder = new StringBuilder("");
        builder.append("  江西理工大学创办于1958年，原名江西冶金学院，1988年更名为南方冶金学院，2004年更名为江西理工大学。学校曾先后隶属于冶金工业部、中国有色金属工业总公司，2013年成为江西省人民政府、工业和信息化部、教育部共建高校。学校是一所以理工为主，工学、理学、经济学、管理学、法学、文学、教育学、艺术学等八大学科协调发展，博、硕士研究生教育与本科教育并举，面向全国招生和就业并有权接收华侨及港澳台学生的教学研究型大学；是宝钢教育奖评审高校；是我国有色金属工业和钢铁工业重要的人才培养和科研基地，被誉为“有色冶金人才摇篮”。");
        builder.append("/r/n");
        builder.append("  学校在赣州、南昌两地有4个校区，校本部位于享有“世界钨都”、“稀土王国”、“客家摇篮”、“红色故都”之美誉的国家历史文化名城——江西省赣州市。学校1958年开办本科教育，1980年开始硕士研究生教育，2013年开始博士研究生教育。现有14个学院，18个科研院所，全日制在校生约3万人。毕业生以其专业适应性广和“为人诚实、基础扎实、工作踏实”的特点而广受社会欢迎，一次就业率一直保持在江西高校前列，并于2011年荣膺全国毕业生就业典型经验高校“五十强”。据中国“世界500强”企业CEO毕业院校排名榜显示，我校在2012年中国“世界500强”企业CEO毕业院校并列第5位。");
        builder.append("/r/n");
        builder.append("  学校拥有1个博士后科研流动站、1个博士后科研工作站、1个“离子型稀土资源开发利用”博士人才培养项目、16个一级学科硕士点、69个二级学科硕士点、15个工程硕士培养领域、60个本科专业（其中56个专业列入江西、安徽、甘肃、河南、河北、内蒙古、海南、青海、新疆、贵州、广西、四川、山东等24个省份本科第一批次招生），具有推荐优秀本科生免试攻读硕士学位资格，是工商管理硕士（MBA）、法律硕士（JM）、工程管理硕士（MEM）培养单位。学校拥有国家特色专业3个、国家级卓越计划专业6个、省级高水平学科2个、省级重点学科4个、省级示范性硕士点2个、江西省卓越计划专业6个、江西省特色专业19个。");
        builder.append("/r/n");
        builder.append("  学校建立了一支梯队结构合理、学术水平高、治学严谨的师资队伍。现有教职工近2000人，其中中国科学院、中国工程院双聘院士共3人，“新世纪百千万人才工程”国家级人选、教育部新世纪优秀人才、国家杰出青年科学基金获得者、国家有突出贡献的中青年专家、享受国务院特殊津贴人员、全国模范教师、优秀教师60余人；“井冈学者”特聘教授2人，省部级主要学科学术和技术带头人、“赣鄱英才555工程”领军人才、“井冈之星”青年科学家培养对象、“新世纪百千万人才工程”人选和高等学校中青年学科带头人近200人。还聘请了古德生、周尧和、邱定蕃等一批院士和130余名国内外知名专家、学者为学校的兼职或客座教授，并聘有多名外籍教师在校常年任教。");
        builder.append("/r/n");
        builder.append("  以质量立校、办一流学府。学校坚持“教学优先、教师优先、学生优先”的工作原则和“教学是否满意、教师是否满意、学生是否满意”的工作标准，引导优质教学资源向教学一线集聚。教学科研设备达到全国先进，拥有国家铜冶炼及加工工程技术研究中心、国家离子型稀土资源高效开发利用工程技术研究中心、 离子型稀土资源开发及应用省部共建教育部实验室、 钨资源高效开发及应用技术教育部工程研究中心、国家产品质量监督检验中心、院士工作站等一批国家科研平台、4个国家级工程实践教育中心和1个国家级实验教学示范中心，还有3个省“2011计划协同创新中心”，1个海智计划工作站，14个省级重点实验室、工程技术研究中心和人文社科重点研究基地，8个省级基础实验教学示范中心，25个实验中心和100多个实习基地。");
        builder.append("/r/n");
        return builder.toString();
    }

    private List<Academy> getAcademiesRelation(School relaSchool) {
        List<Academy> academies = new ArrayList<>();
        Academy jc = new Academy();
        jc.setAcademyName("建筑与测绘工程学院");
        jc.setAcademyAddress(null);
        jc.setAcademyIntroduction(getJCAcademyIntroduction());
        jc.setAcademyPhoneNumber(null);
        jc.setAcademyEmail("jcxy01@126.com");
        jc.setAcademyLocation(null);
        jc.setAcademyLogoImgUrl(null);
        jc.setSchool(relaSchool);
        jc.setSpecialities(null);
        return academies;
    }

    private String getJCAcademyIntroduction() {
        StringBuilder builder = new StringBuilder("");
        builder.append("  学院的发展历程最早可追溯至1958年江西冶金学院建校时期的矿业系，根据学科链拓展需要，学校创办了矿山测量工程专业，并于恢复高考的第一年即1977年开始招生，迄今已有近30年的培养历程；从采矿和测量学科延伸出土建类学科，1992年起开始创办土木工程专业，之后各学科逐步发展，从而形成了测绘与土木两大学科体系，学校于2006年成立了建筑与测绘工程学院。");
        builder.append("/r/n");
        builder.append("  学院现有教职工100余人，其中中科院双聘院士1人，教授15人，副教授37人，博士生导师3人，硕士生导师40人。具有国务院特殊津贴获得者、全国优秀教师、江西省“赣鄱英才555工程”领军人才、江西省主要学科学术和技术带头人、江西省高校中青年学科带头人、江西省百千万人才工程等荣誉称号的拔尖人才近20人次。教师队伍中近40人拥有博士学位，10余人具有海外研修背景，一大批老师具有实际工程经验。现有全日制学生2300余人，其中本科生近2000人，硕士研究生300余人。");
        builder.append("/r/n");
        builder.append("  学院现有土木工程、测绘工程、城乡规划、给排水科学与工程、地理信息科学、建筑环境与能源应用工程、建筑学7个本科专业，其中测绘工程是江西省首批本科品牌专业，也是国家卓越工程师计划专业。土木工程是江西省特色专业，也是校级卓越工程师计划专业，分建筑工程和道路与桥梁两个方向；地理信息科学专业是从测绘工程专业的学科延伸出来，是校级综合改革示范专业，也是我校考研率最高的专业之一；给排水科学与工程专业、建筑环境与能源应用工程、城乡规划与建筑学等专业均各具特色和品牌，深受广大考生的青睐和社会的认可。学院还有省级教学团队2个，省级重点实验室、省级实验教学示范中心和省级创新实验区各1个，省级优质和精品课程4门，省级双语教学示范课程1门。");
        builder.append("/r/n");
        builder.append("  学院现有的测绘科学与技术学科为江西省“十二五”重点一级学科，土木工程、地理学、力学3个一级学科为校级重点学科。学院现有测绘科学与技术、土木工程2个一级学科硕士学位授权点，有大地测量学与测量工程、岩土工程、工程力学、地图学与地理信息系统、地图制图学与地理信息工程、摄影测量与遥感、结构工程、市政工程、桥梁与隧道工程、供热、供燃气、通风与空调工程等11个理工科二级学科硕士学位授权点。另外学院还有测绘工程、建筑与土木工程2个工程领域招收专业学位及工程硕士研究生。");
        builder.append("/r/n");
        builder.append("  学院目前形成了6系1教研室、3实验中心和2个研究院的构架体系。其中6系1教研室分别是测绘工程系、土木工程系、城乡规划与建筑学系、地理信息科学系、市政工程系、建筑环境与能源应用工程系和力学教研室，属于教学机构；3个实验中心分别是土木与力学、测绘与地信和城规与建筑学等实验中心，属于实验教学机构。中心拥有土木工程实验室、测绘工程实验室、城市规划与建筑实验室、给水排水工程实验室、地理信息系统实验室、遥感数据处理与分析实验室、建筑环境与设备工程实验室、资源环境与城乡规划管理实验室、力学实验室等。实验仪器设备总价值近4000万元。另外，学院建有校内外教学实习基地20多个，具有良好的教学与科研条件。2个研究院分别是城乡规划与建筑研究院、测绘与地理信息研究院，属于科研机构，2个研究院下设测绘工程、市政工程等9个研究所。");
        builder.append("/r/n");
        return builder.toString();
    }

    private void JxustRealtionJC() {
        BmobQuery<Academy> query = new BmobQuery<>();
        query.getObject(getContext(), "0e390593ab", new GetListener<Academy>() {
            @Override
            public void onSuccess(final Academy academy) {
                MLog.i(TAG,"Relation Success");
                BmobQuery<School> query1 = new BmobQuery<>();
                query1.getObject(getContext(), "e40489eba1", new GetListener<School>() {
                    @Override
                    public void onSuccess(School school) {
                        BmobRelation relation = new BmobRelation();
                        relation.add(academy);
                        school.setAcademies(relation);
                        school.update(getContext());
                        MLog.i(TAG,"Relation Success");
                    }

                    @Override
                    public void onFailure(int i, String s) {

                    }
                });
            }

            @Override
            public void onFailure(int i, String s) {

            }
        });
    }

}