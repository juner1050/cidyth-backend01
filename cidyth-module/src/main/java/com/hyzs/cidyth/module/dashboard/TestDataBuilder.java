package com.hyzs.cidyth.module.dashboard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import com.hyzs.cidyth.module.base.entity.Cases;
import com.hyzs.cidyth.module.clue.entity.Clue;
import com.hyzs.cidyth.module.dashboard.vo.CaseSolvedOfOrgType;
import com.hyzs.cidyth.module.dashboard.vo.FeedBackQuality;
import com.hyzs.cidyth.module.dashboard.vo.HonorCanvas;
import com.hyzs.cidyth.module.demand.entity.Demand;
import com.hyzs.cidyth.module.msg.entity.Info;
import com.hyzs.cidyth.module.uc.vo.Dept;
import com.hyzs.cidyth.module.uc.vo.DeptCategory;
import com.hyzs.cidyth.module.uc.vo.PolityLevel;
import com.hyzs.cidyth.module.uc.vo.User;

public class TestDataBuilder {
	private static int TEST_DATA_RECORDS = 60;
	public static List<DeptCategory> deptCategories = new ArrayList<DeptCategory>() {
		{
			add(new DeptCategory(40, "网络侦查"));
			add(new DeptCategory(41, "技术侦查"));
			add(new DeptCategory(42, "视频侦查"));
			add(new DeptCategory(44, "情报侦查"));
		}
	};
	public static List<Dept> subDeptList = new ArrayList<Dept>() {
		{
			add(new Dept("440303000000", "深圳市公安局罗湖分局", "440300000000"));
			add(new Dept("440304000000", "深圳市公安局福田分局", "440300000000"));
			add(new Dept("440305000000", "深圳市公安局南山分局", "440300000000"));
			add(new Dept("440306000000", "深圳市公安局宝安分局", "440300000000"));
			add(new Dept("440307000000", "深圳市公安局龙岗分局", "440300000000"));
			add(new Dept("440308000000", "深圳市公安局盐田分局", "440300000000"));
			add(new Dept("440309000000", "深圳市公安局光明分局", "440300000000"));
			add(new Dept("440310000000", "深圳市公安局坪山分局", "440300000000"));
			add(new Dept("440311000000", "深圳市公安局龙华分局", "440300000000"));
			add(new Dept("440312000000", "深圳市公安局大鹏分局", "440300000000"));
			add(new Dept("440343000000", "深圳市公安局森林分局", "440300000000"));
			add(new Dept("440380000000", "广东海事公安局深圳分局", "440300000000"));
			add(new Dept("440395000000", "深圳市公安局机场分局", "440300000000"));
			add(new Dept("440396000000", "深圳市公安局公交分局", "440300000000"));
			add(new Dept("440398000000", "深圳市公安局大亚湾分局", "440300000000"));
			add(new Dept("440399000000", "深圳市公安局东深分局", "440300000000"));

			add(new Dept("440303500000", "深圳市公安局东湖派出所", "440303000000"));
			add(new Dept("440303510000", "深圳市公安局黄贝派出所", "440303000000"));
			add(new Dept("440303520000", "深圳市公安局南湖派出所", "440303000000"));
			add(new Dept("440303530000", "深圳市公安局桂园派出所", "440303000000"));
			add(new Dept("440303540000", "深圳市公安局笋岗派出所", "440303000000"));
			add(new Dept("440303550000", "深圳市公安局东门派出所", "440303000000"));
			add(new Dept("440303560000", "深圳市公安局翠竹派出所", "440303000000"));
			add(new Dept("440303570000", "深圳市公安局莲塘派出所", "440303000000"));
			add(new Dept("440303580000", "深圳市公安局东晓派出所", "440303000000"));
			add(new Dept("440303590000", "深圳市公安局清水河派出所", "440303000000"));
			add(new Dept("440303600000", "深圳市公安局罗湖口岸派出所", "440303000000"));

			add(new Dept("440304500000", "深圳市公安局通心岭派出所", "440304000000"));
			add(new Dept("440304510000", "深圳市公安局南园派出所", "440304000000"));
			add(new Dept("440304520000", "深圳市公安局华富派出所", "440304000000"));
			add(new Dept("440304530000", "深圳市公安局莲花派出所", "440304000000"));
			add(new Dept("440304540000", "深圳市公安局福田派出所", "440304000000"));
			add(new Dept("440304550000", "深圳市公安局沙头派出所", "440304000000"));
			add(new Dept("440304560000", "深圳市公安局梅林派出所", "440304000000"));
			add(new Dept("440304570000", "深圳市公安局香蜜湖派出所", "440304000000"));
			add(new Dept("440304580000", "深圳市公安局福保派出所", "440304000000"));
			add(new Dept("440304590000", "深圳市公安局天安派出所", "440304000000"));
			add(new Dept("440304600000", "深圳市公安局八卦岭派出所", "440304000000"));
			add(new Dept("440304610000", "深圳市公安局景田派出所", "440304000000"));
			add(new Dept("440304620000", "深圳市公安局皇岗口岸派出所", "440304000000"));
			add(new Dept("440304630000", "深圳市公安局福强派出所", "440304000000"));
			add(new Dept("440304650000", "深圳市福田区华强北派出所", "440304000000"));
			add(new Dept("440304880000", "深圳市公安局福田分局网络派出所", "440304000000"));

			add(new Dept("440305500000", "深圳市公安局南头派出所", " 440305000000"));
			add(new Dept("440305510000", "深圳市公安局南山派出所", " 440305000000"));
			add(new Dept("440305520000", "深圳市公安局沙河派出所", " 440305000000"));
			add(new Dept("440305530000", "深圳市公安局粤海派出所", " 440305000000"));
			add(new Dept("440305540000", "深圳市公安局蛇口派出所", " 440305000000"));
			add(new Dept("440305550000", "深圳市公安局招商派出所", " 440305000000"));
			add(new Dept("440305560000", "深圳市公安局西丽派出所", " 440305000000"));
			add(new Dept("440305570000", "深圳市公安局桃源派出所", " 440305000000"));
			add(new Dept("440305580000", "深圳市公安局高新技术园区派出所", " 440305000000"));
			add(new Dept("440305590000", "深圳市公安局塘朗派出所", " 440305000000"));
			add(new Dept("440305600000", "深圳市公安局深圳湾派出所", " 440305000000"));
			add(new Dept("440305950000", "深圳市公安局后海边防派出所", " 440305000000"));
			add(new Dept("440305960000", "深圳市公安局蛇口边防派出所", " 440305000000"));
			add(new Dept("440305970000", "深圳市公安局内伶仃岛边防派出所", " 440305000000"));

			add(new Dept("440300191300", "深圳市公安局刑事侦查局九大队"));
		}
	};
	public static List<User> users = new ArrayList<User>() {
		{
			add(new User("000031", "江铁如", "440300000000"));
			add(new User("000055", "梁景常", "440300000000"));
			add(new User("000062", "李仲华", "440300000000"));
			add(new User("000119", "杨成崑", "440300000000"));
			add(new User("000175", "魏品帅", "440300000000"));
			add(new User("000182", "陈沂雄", "440300000000"));
			add(new User("000184", "胡月明", "440300000000"));
			add(new User("000195", "李锦桂", "440300000000"));
			add(new User("000198", "余力", "440300000000"));
			add(new User("000206", "云知道", "440300000000"));
			add(new User("000273", "李荣华", "440300000000"));
			add(new User("000299", "居武军", "440300000000"));
			add(new User("000309", "陈 敏", "440300000000"));
			add(new User("000344", "胡善斌", "440300000000"));
			add(new User("000369", "蓝进雄", "440300000000"));
			add(new User("000398", "吴列告", "440300000000"));
			add(new User("000463", "郑穗红", "440300000000"));
			add(new User("000484", "梁玉咏", "440300000000"));
			add(new User("000523", "李汉雄", "440300000000"));
			add(new User("000611", "苏良基", "440300000000"));
			add(new User("000655", "张燕鸾", "440300000000"));
			add(new User("000814", "刘备胜", "440300000000"));
			add(new User("000815", "杨伯旺", "440300000000"));
			add(new User("000830", "邱小栓", "440300000000"));
			add(new User("000880", "张远坤", "440300000000"));
			add(new User("000926", "秦主华", "440300000000"));
			add(new User("001006", "朱铁成", "440300000000"));
			add(new User("001038", "郑轶", "440300000000"));
			add(new User("001127", "冯明帅", "440300000000"));
			add(new User("001133", "马洪刚", "440300000000"));
			add(new User("001136", "任颖峰", "440300000000"));
			add(new User("001156", "谢昭翔", "440300000000"));
			add(new User("001169", "李秀焕", "440300000000"));
			add(new User("001170", "张勇锋", "440300000000"));
			add(new User("001187", "邓龙腾", "440300000000"));
			add(new User("001188", "杨亮", "440300000000"));
			add(new User("001252", "黄蕾", "440300000000"));
			add(new User("001293", "申勇强", "440300000000"));
			add(new User("001306", "廖志伟", "440300000000"));
			add(new User("001307", "李广平", "440300000000"));
			add(new User("001312", "易陟", "440300000000"));
			add(new User("001327", "黄伟群", "440300000000"));
			add(new User("001348", "郭宝山", "440300000000"));
			add(new User("001403", "张广奎", "440300000000"));
			add(new User("001405", "詹仲全", "440300000000"));
			add(new User("001443", "周湘", "440300000000"));
			add(new User("001477", "杨向东", "440300000000"));
			add(new User("001504", "杨宇", "440300000000"));
			add(new User("001518", "周世达", "440300000000"));
			add(new User("001523", "程建平", "440300000000"));
			add(new User("001532", "唐卫平", "440300000000"));
			add(new User("001573", "叶美洋", "440300000000"));
			add(new User("001574", "陈  武", "440300000000"));
			add(new User("001590", "陈育", "440300000000"));
			add(new User("001605", "梁振林", "440300000000"));
			add(new User("001607", "温志锋", "440300000000"));
			add(new User("001627", "尚晓丹", "440300000000"));
			add(new User("001634", "李绪帅", "440300000000"));
			add(new User("001646", "廖声银", "440300000000"));
			add(new User("001649", "陈国治", "440300000000"));
			add(new User("001651", "张清泉", "440300000000"));
			add(new User("001657", "张浩春", "440300000000"));
			add(new User("001723", "罗戈斌", "440300000000"));
			add(new User("001766", "陈少民", "440300000000"));
			add(new User("001769", "阮福成", "440300000000"));
			add(new User("001776", "省厅指挥中心赵文忠", "440300000000"));
			add(new User("001793", "黄慧贞", "440300000000"));
			add(new User("001883", "李健生", "440300000000"));
			add(new User("001920", "王国景", "440300000000"));
			add(new User("001928", "袁炎良", "440300000000"));
			add(new User("001939", "郑耀庆", "440300000000"));
			add(new User("001943", "孙广斌", "440300000000"));
			add(new User("001958", "许炜程", "440300000000"));
			add(new User("001979", "王兴宇", "440300000000"));
			add(new User("001996", "姚磊", "440300000000"));
			add(new User("002069", "邓熙", "440300000000"));
			add(new User("002073", "罗恒宏", "440300000000"));
			add(new User("002112", "徐玲", "440300000000"));
			add(new User("002120", "陈辉", "440300000000"));
			add(new User("002157", "朱明伟", "440300000000"));
			add(new User("002167", "林国辉", "440300000000"));
			add(new User("002181", "杨  洋", "440300000000"));
			add(new User("002187", "刘树人", "440300000000"));
			add(new User("002190", "陆路", "440300000000"));
			add(new User("002234", "郭济南", "440300000000"));
			add(new User("002258", "祝戎枭", "440300000000"));
			add(new User("002273", "朱小林", "440300000000"));
			add(new User("002275", "童波", "440300000000"));
			add(new User("002277", "吴彦", "440300000000"));
			add(new User("002286", "张毅", "440300000000"));
			add(new User("002312", "曾寿雄", "440300000000"));
			add(new User("002349", "杨思坚", "440300000000"));
			add(new User("002364", "梁素玲", "440300000000"));
			add(new User("002417", "曹群", "440300000000"));
			add(new User("002424", "刘治民", "440300000000"));
			add(new User("002452", "江南", "440300000000"));
			add(new User("002484", "雷群启", "440300000000"));
			add(new User("002512", "王冠中", "440300000000"));
			add(new User("002522", "杨亚来", "440300000000"));
			add(new User("002535", "张岩", "440300000000"));
			add(new User("002555", "麦媛玲", "440300000000"));
			add(new User("002557", "许俊明", "440300000000"));
			add(new User("002564", "宁惠军", "440300000000"));
			add(new User("002565", "黄伟", "440300000000"));
			add(new User("002571", "刘  辉", "440300000000"));
			add(new User("002599", "夏明付", "440300000000"));
			add(new User("002607", "钟道迈", "440300000000"));
			add(new User("002615", "魏龙泉", "440300000000"));
			add(new User("002636", "凌碧波", "440300000000"));
			add(new User("002642", "梁昊帮", "440300000000"));
			add(new User("002643", "姜宝", "440300000000"));
			add(new User("002653", "邓  炜", "440300000000"));
			add(new User("002667", "贺建军", "440300000000"));
			add(new User("002671", "王基业", "440300000000"));
			add(new User("002731", "魏云兰", "440300000000"));
			add(new User("002738", "董信奇", "440300000000"));
			add(new User("002755", "胡志军", "440300000000"));
			add(new User("002756", "汪国华", "440300000000"));
			add(new User("002779", "王 廷", "440300000000"));
			add(new User("002780", "郑青艳", "440300000000"));
			add(new User("002807", "伍一丽", "440300000000"));
			add(new User("002826", "申雄坚", "440300000000"));
			add(new User("002842", "高健江", "440300000000"));
			add(new User("002862", "陈刚", "440300000000"));
			add(new User("002874", "唐鹏", "440300000000"));
			add(new User("002889", "张志豪", "440300000000"));
			add(new User("002897", "臧腾", "440300000000"));
			add(new User("013322", "马越", "440300000000"));
			add(new User("013341", "马曦", "440300000000"));
			add(new User("013356", "林宝茹", "440300000000"));
			add(new User("013367", "庄志刚", "440300000000"));
			add(new User("013375", "崔伊爽", "440300000000"));
			add(new User("018133", "廖宇辉", "440300000000"));
			add(new User("0204", "邓鑫", "440300000000"));
			add(new User("02511", "潘爱斌", "440300000000"));
			add(new User("027577", "吴友银", "440300000000"));
			add(new User("0305", "姚世明", "440300000000"));
			add(new User("050001", "徐文海", "440300000000"));
			add(new User("050002", "施志刚", "440300000000"));
			add(new User("050003", "苏振威", "440300000000"));
			add(new User("050004", "罗威", "440300000000"));
			add(new User("050005", "甘桂平", "440300000000"));
			add(new User("050006", "任亿", "440300000000"));
			add(new User("050007", "谷云宏", "440300000000"));
			add(new User("050008", "李龙文", "440300000000"));
			add(new User("050009", "周兆翔", "440300000000"));
			add(new User("050010", "官尔杰", "440300000000"));
			add(new User("050012", "王依军", "440300000000"));
			add(new User("050017", "王剑平", "440300000000"));
			add(new User("050172", "张展祥", "440300000000"));
			add(new User("050176", "王会全", "440300000000"));
			add(new User("050428", "陈春雄(退休)", "440300000000"));
			add(new User("050432", "梁耀刚", "440300000000"));
			add(new User("050436", "黄宏", "440300000000"));
			add(new User("050521", "应义明", "440300000000"));
			add(new User("050524", "张立", "440300000000"));
			add(new User("050711", "吴鸿明", "440300000000"));
			add(new User("051150", "张蕾", "440300000000"));
			add(new User("051151", "张寒玉", "440300000000"));
			add(new User("051248", "胡晓明", "440300000000"));
			add(new User("051397", "孙博", "440300000000"));
			add(new User("051406", "师娜丽", "440300000000"));
			add(new User("051657", "李丽蓉", "440300000000"));
			add(new User("054366", "孙流涛", "440300000000"));
			add(new User("054461", "汪洪", "440300000000"));
			add(new User("054463", "主临宁", "440300000000"));
			add(new User("054465", "岑显春", "440300000000"));
			add(new User("054475", "郑双怀", "440300000000"));
			add(new User("054482", "余峰", "440300000000"));
			add(new User("054483", "李冉", "440300000000"));
			add(new User("054485", "杨康才", "440300000000"));
			add(new User("054507", "潘杰", "440300000000"));
			add(new User("054512", "王维斯", "440300000000"));
			add(new User("054519", "孙玉莺", "440300000000"));
			add(new User("054623", "王丽敏", "440300000000"));
			add(new User("054628", "曲晓顺", "440300000000"));
			add(new User("054674", "杨枫", "440300000000"));
			add(new User("055151", "申少保", "440300000000"));
			add(new User("055164", "李毅", "440300000000"));
			add(new User("055225", "李冉", "440300000000"));
			add(new User("055250", "椎岭", "440300000000"));
			add(new User("055695", "佘俊桐", "440300000000"));
			add(new User("055696", "王旭超", "440300000000"));
			add(new User("055697", "林佳", "440300000000"));
			add(new User("055830", "姚眉", "440300000000"));
			add(new User("056001", "朱杰峰", "440300000000"));
			add(new User("056041", "吴伟华", "440300000000"));
			add(new User("056075", "乔敏", "440300000000"));
			add(new User("056974", "杨进", "440300000000"));
			add(new User("057050", "王榜金", "440300000000"));
			add(new User("057359", "黄怀宇", "440300000000"));
			add(new User("057431", "陈峰峰", "440300000000"));
			add(new User("057440", "陈晓兰", "440300000000"));
			add(new User("057677", "刘陈月", "440300000000"));
			add(new User("058006", "苏振威", "440300000000"));
			add(new User("058010", "王竣民", "440300000000"));
			add(new User("058076", "周杨", "440300000000"));
			add(new User("059154", "杨喜春", "440300000000"));
			add(new User("059512", "高飞", "440300000000"));
			add(new User("059538", "万海峰", "440300000000"));
			add(new User("059614", "张鹏", "440300000000"));
			add(new User("060001", "施志刚", "440300000000"));
			add(new User("060504", "刘劼", "440300000000"));
			add(new User("060623", "刘国基", "440300000000"));
			add(new User("060718", "熊继林", "440300000000"));
			add(new User("060792", "曹玉忠", "440300000000"));
			add(new User("06125a", "（作废）", "440300000000"));
			add(new User("061363", "陈松磊", "440300000000"));
			add(new User("061396", "李-斌", "440300000000"));
			add(new User("061552", "黄文辉", "440300000000"));
			add(new User("061609", "杨海琨", "440300000000"));
			add(new User("061871", "温金星", "440300000000"));
			add(new User("061912", "蒋映捷", "440300000000"));
			add(new User("063730", "杨再平", "440300000000"));
			add(new User("063756", "朱为刚", "440300000000"));
			add(new User("064350", "张国粤", "440300000000"));
			add(new User("064527", "黄贵兴", "440300000000"));
			add(new User("064919", "彭雪冰", "440300000000"));
			add(new User("064968", "黄建航", "440300000000"));
			add(new User("065103", "蒲雄峰", "440300000000"));
			add(new User("065155", "洪侠", "440300000000"));
			add(new User("065617", "谭明强", "440300000000"));
			add(new User("065704", "林敦勇", "440300000000"));
			add(new User("066086", "林越群", "440300000000"));
			add(new User("066923", "李辉雄", "440300000000"));
			add(new User("067036", "高鹏", "440300000000"));
			add(new User("067088", "黄锴", "440300000000"));
			add(new User("067170", "梁冠勋", "440300000000"));
			add(new User("067561", "曹利坤", "440300000000"));
			add(new User("067674", "张振文", "440300000000"));
			add(new User("067814", "邬斌", "440300000000"));
			add(new User("067815", "唐晓春", "440300000000"));
			add(new User("067817", "江聪戌", "440300000000"));
			add(new User("067818", "伍振宇", "440300000000"));
			add(new User("067821", "林伟文", "440300000000"));
			add(new User("067825", "赖智敏", "440300000000"));
			add(new User("067827", "黄晓锋", "440300000000"));
			add(new User("067839", "易维", "440300000000"));
			add(new User("067841", "陈柱？", "440300000000"));
			add(new User("067844", "张学明", "440300000000"));
			add(new User("067847", "许细昌", "440300000000"));
			add(new User("067848", "周宇童", "440300000000"));
			add(new User("067850", "刘奎财", "440300000000"));
			add(new User("067898", "陈刃", "440300000000"));
			add(new User("067950", "许 磊", "440300000000"));
			add(new User("068112", "任贞妮", "440300000000"));
			add(new User("068140", "李鑫", "440300000000"));
			add(new User("068521", "张书菀", "440300000000"));
			add(new User("068599", "赵智", "440300000000"));
			add(new User("068688", "黄远忠(退休)", "440300000000"));
			add(new User("068944", "罗银国", "440300000000"));
			add(new User("068957", "苏晨", "440300000000"));
			add(new User("069149", "张一菲", "440300000000"));
			add(new User("069730", "雷岩", "440300000000"));
			add(new User("069994", "林瀚", "440300000000"));
			add(new User("069995", "李斌", "440300000000"));
			add(new User("070052", "贾骅俊", "440300000000"));
			add(new User("071058", "刘涛", "440300000000"));
			add(new User("071579", "付文超", "440300000000"));
			add(new User("071732", "徐植洪", "440300000000"));
			add(new User("071753", "陶孔明", "440300000000"));
			add(new User("072876", "周宇亮", "440300000000"));
			add(new User("073162", "潘子文", "440300000000"));
			add(new User("083784", "唐照明", "440300000000"));
			add(new User("092571", "丁帆", "440300000000"));
			add(new User("092660", "陈科錞", "440300000000"));
			add(new User("094280", "杨文亮", "440300000000"));
			add(new User("098042", "石小东", "440300000000"));
			add(new User("098060", "董状", "440300000000"));
			add(new User("105453", "谭斌", "440300000000"));
			add(new User("111997", "刘武聪", "440300000000"));
			add(new User("123605", "李庆乐", "440300000000"));
			add(new User("137354", "徐振东", "440300000000"));
			add(new User("150343", "曾沛森", "440300000000"));
			add(new User("150405", "谢雨风", "440300000000"));
			add(new User("156404", "徐果夫", "440300000000"));
			add(new User("156405", "黎彬洪", "440300000000"));
			add(new User("159475", "唐亮", "440300000000"));
			add(new User("176744", "陈哲", "440300000000"));
			add(new User("192944", "莫洪武", "440300000000"));
			add(new User("193997", "罗新铭", "440300000000"));
			add(new User("201814", "李志华", "440300000000"));
			add(new User("202173", "罗胤晟", "440300000000"));
			add(new User("206044", "郑锦辉", "440300000000"));
			add(new User("211408", "陈湛", "440300000000"));
			add(new User("217071", "温华明", "440300000000"));
			add(new User("2222", "省厅出入境查询用户", "440300000000"));
			add(new User("225058", "陈燕辉", "440300000000"));
			add(new User("250033", "刘春光", "440300000000"));
			add(new User("260259", "黄旭平", "440300000000"));
			add(new User("50001", "刘庆生", "440300000000"));
			add(new User("50002", "施志刚", "440300000000"));
			add(new User("50003", "王依军", "440300000000"));
			add(new User("50005", "王晓南", "440300000000"));
			add(new User("510111", "周剑锋", "440300000000"));
			add(new User("a02002", "罗光明", "440300000000"));
			add(new User("a13301", "黄洪", "440300000000"));
			add(new User("a13302", "王智民", "440300000000"));
			add(new User("a13303", "杨静宏", "440300000000"));
			add(new User("a13317", "曲玉斌", "440300000000"));
			add(new User("a13318", "任恩顺", "440300000000"));
			add(new User("a13322", "马  越", "440300000000"));
			add(new User("a13325", "吴开清", "440300000000"));
			add(new User("a13326", "谢  华", "440300000000"));
			add(new User("a13329", "任磊石", "440300000000"));
			add(new User("a13330", "赵  春", "440300000000"));
			add(new User("a13333", "颜玉英", "440300000000"));
			add(new User("a13334", "赫利斌", "440300000000"));
			add(new User("a13341", "马  曦", "440300000000"));
			add(new User("a13350", "王  航", "440300000000"));
			add(new User("a13351", "王  卉", "440300000000"));
			add(new User("a13352", "刘  冰", "440300000000"));
			add(new User("a13354", "李亭亭", "440300000000"));
			add(new User("a13355", "作废", "440300000000"));
			add(new User("a13357", "赵红霞", "440300000000"));
			add(new User("a13359", "何海涛", "440300000000"));
			add(new User("a13360", "纪明亮", "440300000000"));
			add(new User("a13361", "吴春生", "440300000000"));
			add(new User("a13362", "刘达明", "440300000000"));
			add(new User("a13364", "单亚男", "440300000000"));
			add(new User("a13365", "王  鹏", "440300000000"));
			add(new User("a13367", "庄志刚", "440300000000"));
			add(new User("a13369", "曲艺腾", "440300000000"));
			add(new User("a13370", "陈元巍", "440300000000"));
			add(new User("a13371", "张志会", "440300000000"));
			add(new User("a13372", "季春莹", "440300000000"));
			add(new User("a13374", "赵  锴", "440300000000"));
			add(new User("a13375", "崔伊爽", "440300000000"));
			add(new User("a13380", "卢咿静", "440300000000"));
			add(new User("a13382", "李惠莉", "440300000000"));
			add(new User("a13383", "吕勇林", "440300000000"));
			add(new User("a13384", "俞子舒", "440300000000"));
			add(new User("a13385", "隋尚蓉", "440300000000"));
			add(new User("A50001", "李铭", "440300000000"));
			add(new User("bf0000", "边防总队督查员", "440300000000"));
			add(new User("bf2873", "贤琳", "440300000000"));

			add(new User("050858", "林忠全", "440300191300"));
			add(new User("051172", "罗春明", "440300191300"));
			add(new User("051173", "钟穗广", "440300191300"));
			add(new User("051200", "黎雄斌", "440300191300"));
			add(new User("051206", "陈伟强", "440300191300"));
			add(new User("051238", "黄立铭", "440300191300"));
			add(new User("051285", "李锐锋", "440300191300"));
			add(new User("051307", "侯郁", "440300191300"));
			add(new User("051354", "谢旻", "440300191300"));
			add(new User("051363", "曾晓芳", "440300191300"));
			add(new User("051412", "谢伟群", "440300191300"));
			add(new User("051413", "杨兴俊", "440300191300"));
			add(new User("051439", "韩捷", "440300191300"));
			add(new User("055939", "李德阳子", "440300191300"));
			add(new User("063003", "龚晓斌", "440300191300"));
			add(new User("063552", "彭晔", "440300191300"));
			add(new User("064280", "成伟彬", "440300191300"));
			add(new User("066307", "洪喜", "440300191300"));
			add(new User("066951", "姚键", "440300191300"));
			add(new User("067226", "郑毅", "440300191300"));
			add(new User("067250", "黄宇", "440300191300"));
			add(new User("068240", "陈节", "440300191300"));
			add(new User("071069", "欧阳丹", "440300191300"));
			add(new User("071071", "王博", "440300191300"));
			add(new User("071078", "李显龙", "440300191300"));
		}
	};
	public static List<PolityLevel> polityLevel = new ArrayList<PolityLevel>() {
		{
			add(new PolityLevel(46, "分局"));
			add(new PolityLevel(47, "支队"));
			add(new PolityLevel(48, "大队"));
			add(new PolityLevel(49, "派出所"));
			add(new PolityLevel(50, "片区民警"));
			add(new PolityLevel(51, "市局"));
		}
	};

	public static CaseSolvedOfOrgType buildDailyCaseSolveSurveyByOrganizationType() {
		CaseSolvedOfOrgType result = new CaseSolvedOfOrgType();
		int totalCases = 0;
		for (int j = 0; j < TestDataBuilder.TEST_DATA_RECORDS; j++) {
			String r = RandomBuilder.randomString("12345", 1);
			DeptCategory pt = deptCategories.get(Integer.parseInt(r) - 1);

			int count = RandomBuilder.randomNumber(1);
			result.addDemand(pt.getId(), pt.getName(), count);
			int scount = RandomBuilder.randomNumber(1);
			if (count > 100 && scount < count) {
				result.addSignedDemand(pt.getId(), pt.getName(), count - scount);
				result.addReply(pt.getId(), pt.getName(), count - scount);
			} else {
				result.addSignedDemand(pt.getId(), pt.getName(), count);
				result.addReply(pt.getId(), pt.getName(), count);
			}
			totalCases = totalCases + count;
		}
		result.setTotalCase(totalCases + 10);
		return result;
	}

	private static String buildMonthString(Collection<Integer> monthList) {
		StringBuilder strb = new StringBuilder();
		for (Integer m : monthList) {
			strb.append(m);
		}
		return strb.toString();
	}

	private static String buildSubDeptListIndexString() {
		StringBuilder strb = new StringBuilder();
		for (int j = 0; j < subDeptList.size(); j++) {
			strb.append(j + 1);
		}
		return strb.toString();
	}

	public static List<Cases> buildMonthlyCaseOfOrganization(Collection<Integer> monthList) {
		List<Cases> caseList = new ArrayList<Cases>();
		int latestMonth = Ordering.natural().nullsFirst().greatestOf(monthList, 1).get(0);
		for (int j = 0; j < TestDataBuilder.TEST_DATA_RECORDS; j++) {
			String m = RandomBuilder.randomString(buildMonthString(monthList), ("" + latestMonth).length());
			String dwIdx = RandomBuilder.randomString(buildSubDeptListIndexString(),
					("" + subDeptList.size()).length());
			int apperence = 0;
			while (apperence < 1) {
				apperence = RandomBuilder.randomNumber(3);
			}
			for (int c = 0; c < apperence; c++) {
				Cases cas = new Cases();
				cas.setAb(m);
				cas.setSljsdw(subDeptList.get(Integer.parseInt(dwIdx) - 1).getCode());
				caseList.add(cas);
			}
		}
		return caseList;
	}

	public static List<Demand> buildMonthlyDemandOfOrganization(Collection<Integer> monthList) {
		List<Demand> demandList = new ArrayList<Demand>();
		int latestMonth = Ordering.natural().nullsFirst().greatestOf(monthList, 1).get(0);
		for (int j = 0; j < TestDataBuilder.TEST_DATA_RECORDS; j++) {
			String m = RandomBuilder.randomString(buildMonthString(monthList), ("" + latestMonth).length());
			String dwIdx = RandomBuilder.randomString(buildSubDeptListIndexString(),
					("" + subDeptList.size()).length());
			int apperence = 0;
			while (apperence < 1) {
				apperence = RandomBuilder.randomNumber(3);
			}
			for (int c = 0; c < apperence; c++) {
				Demand dem = new Demand();
				dem.setAjbh(m);
				dem.setJsdwbh(subDeptList.get(Integer.parseInt(dwIdx) - 1).getCode());
				demandList.add(dem);
			}
		}
		return demandList;
	}

	public static List<Clue> buildMonthlyFeedbackOfOrganization() {
		List<Clue> clewList = new ArrayList<Clue>();
		for (int j = 0; j < TestDataBuilder.TEST_DATA_RECORDS; j++) {
			Clue clew = new Clue();
			String dwIdx = RandomBuilder.randomString(buildSubDeptListIndexString(),
					("" + subDeptList.size()).length());
			clew.setFkdwbh(subDeptList.get(Integer.parseInt(dwIdx) - 1).getCode());
			int amt = 0;
			while (amt < 1) {
				amt = RandomBuilder.randomNumber(3);
			}
			clew.setSffj(amt);
			clewList.add(clew);
		}
		return clewList;
	}

	public static List<Info> buildMonthlyPublishedInfoOfOrganization(Collection<Integer> monthList) {
		List<Info> infoList = new ArrayList<Info>();
		int latestMonth = Ordering.natural().nullsFirst().greatestOf(monthList, 1).get(0);
		for (int j = 0; j < TestDataBuilder.TEST_DATA_RECORDS; j++) {
			String m = RandomBuilder.randomString(buildMonthString(monthList), ("" + latestMonth).length());
			String dwIdx = RandomBuilder.randomString(buildSubDeptListIndexString(),
					("" + subDeptList.size()).length());
			int apperence = 0;
			while (apperence < 100) {
				apperence = RandomBuilder.randomNumber(5);
			}
			for (int c = 0; c < apperence; c++) {
				// 出现次数
				Info info = new Info();
				info.setAjbh(m);
				info.setFbdwbh(subDeptList.get(Integer.parseInt(dwIdx) - 1).getCode());
				infoList.add(info);
			}
		}
		return infoList;
	}

	public static List<Demand> buildThingsWillBeDone() {
		Set<Demand> demandWillBeProcess = Sets.newHashSet();
		for (int j = 0; j < TestDataBuilder.TEST_DATA_RECORDS; j++) {
			Demand demand = new Demand();
			demand.setId(j);
			if (j < 5) {
				demand.setXqmc("张文举被诈骗案，请求查看手机号码");
			}
			if (j >= 5 && j < 10) {
				demand.setXqmc("李斯贩毒,请求协助搜查窝点罗湖居点赃物");
			}
			if (j >= 10 && j < 15) {
				demand.setXqmc("阿普渡.桑切斯 贩卖人口,请求调取出入境记录");
			}
			if (j >= 15 && j < 20) {
				demand.setXqmc("小渔村民事纠纷，持械斗殴,请求抓获逃逸主要人员");
			}
			if (j >= 20 && j < TestDataBuilder.TEST_DATA_RECORDS) {
				demand.setXqmc("广场路车祸，请求支援调取肇事车辆车牌号");
			}
			demandWillBeProcess.add(demand);
		}
		return Lists.newArrayList(demandWillBeProcess);
	}

	public static List<HonorCanvas> buildHonorCanvas(int rank) {
		List<HonorCanvas> result = new ArrayList<HonorCanvas>();
		// for(int j=0;j<TestDataBuilder.TEST_DATA_RECORDS;j++){
		HonorCanvas hc1 = new HonorCanvas();
		hc1.setOrganizationId(deptCategories.get(0).getId() + "");
		hc1.setOrganizationName(deptCategories.get(0).getName());
		hc1.setTotalAcceptedDemand(55);
		hc1.setTotalSignedDemand(55);
		hc1.setTotalFeedBack(54);
		hc1.setCountOfFeedBackInOneDay(30);
		hc1.setCountOfFeedBackIn3Day(14);
		hc1.setCountOfFeedBackIn30DayExceeded(10);
		FeedBackQuality fbq1 = new FeedBackQuality();
		fbq1.setExcellent(10);
		fbq1.setWell(7);
		fbq1.setGood(5);
		fbq1.setGeneric(29);
		fbq1.setInferior(3);
		hc1.setFeedBackQuality(fbq1);
		hc1.setCompositiveRank(1);

		HonorCanvas hc2 = new HonorCanvas();
		hc2.setOrganizationId(deptCategories.get(1).getId() + "");
		hc2.setOrganizationName(deptCategories.get(1).getName());
		hc2.setTotalAcceptedDemand(49);
		hc2.setTotalSignedDemand(49);
		hc2.setTotalFeedBack(48);
		hc2.setCountOfFeedBackInOneDay(40);
		hc2.setCountOfFeedBackIn3Day(6);
		hc2.setCountOfFeedBackIn30DayExceeded(2);
		FeedBackQuality fbq2 = new FeedBackQuality();
		fbq2.setExcellent(11);
		fbq2.setWell(5);
		fbq2.setGood(7);
		fbq2.setGeneric(24);
		fbq2.setInferior(1);
		hc2.setFeedBackQuality(fbq2);
		hc2.setCompositiveRank(2);

		HonorCanvas hc3 = new HonorCanvas();
		hc3.setOrganizationId(deptCategories.get(2).getId() + "");
		hc3.setOrganizationName(deptCategories.get(2).getName());
		hc3.setTotalAcceptedDemand(55);
		hc3.setTotalSignedDemand(55);
		hc3.setTotalFeedBack(54);
		hc3.setCountOfFeedBackInOneDay(30);
		hc3.setCountOfFeedBackIn3Day(13);
		hc3.setCountOfFeedBackIn30DayExceeded(11);
		FeedBackQuality fbq3 = new FeedBackQuality();
		fbq3.setExcellent(14);
		fbq3.setWell(8);
		fbq3.setGood(8);
		fbq3.setGeneric(22);
		fbq3.setInferior(2);
		hc3.setFeedBackQuality(fbq3);
		hc3.setCompositiveRank(3);

		HonorCanvas hc4 = new HonorCanvas();
		hc4.setOrganizationId(deptCategories.get(3).getId() + "");
		hc4.setOrganizationName(deptCategories.get(3).getName());
		hc4.setTotalAcceptedDemand(49);
		hc4.setTotalSignedDemand(49);
		hc4.setTotalFeedBack(48);
		hc4.setCountOfFeedBackInOneDay(40);
		hc4.setCountOfFeedBackIn3Day(4);
		hc4.setCountOfFeedBackIn30DayExceeded(4);
		FeedBackQuality fbq4 = new FeedBackQuality();
		fbq4.setExcellent(12);
		fbq4.setWell(5);
		fbq4.setGood(8);
		fbq4.setGeneric(18);
		fbq4.setInferior(5);
		hc4.setFeedBackQuality(fbq4);
		hc4.setCompositiveRank(4);

//		HonorCanvas hc5 = new HonorCanvas();
//		hc5.setOrganizationId(deptCategories.get(4).getId() + "");
//		hc5.setOrganizationName(deptCategories.get(4).getName());
//		hc5.setTotalAcceptedDemand(55);
//		hc5.setTotalSignedDemand(55);
//		hc5.setTotalFeedBack(54);
//		hc5.setCountOfFeedBackInOneDay(30);
//		hc5.setCountOfFeedBackIn3Day(13);
//		hc5.setCountOfFeedBackIn30DayExceeded(11);
//		FeedBackQuality fbq5 = new FeedBackQuality();
//		fbq5.setExcellent(16);
//		fbq5.setWell(8);
//		fbq5.setGood(9);
//		fbq5.setGeneric(17);
//		fbq5.setInferior(4);
//		hc5.setFeedBackQuality(fbq5);
//		hc5.setCompositiveRank(5);

		result.add(hc1);
		result.add(hc2);
		result.add(hc3);
		result.add(hc4);
//		result.add(hc5);

		// }
		return result;
	}

	public static void main(String[] args) {
		List data = TestDataBuilder.buildHonorCanvas(5);
		System.out.println(data);
	}
}
