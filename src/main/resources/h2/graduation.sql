create table course
(
    course_id      int auto_increment comment 'id'
        primary key,
    course_no      varchar(25) not null comment '课程编号',
    course_name    varchar(25) null comment '课程名称',
    course_type    varchar(20) not null comment '课程性质',
    user_level     varchar(20) not null comment '学生层次',
    course_time    varchar(25) not null comment '授课时间',
    course_teacher varchar(11) not null comment '主讲教师'
)
    comment '课程表';

create table international_cooperation
(
    ic_id         int auto_increment comment 'id'
        primary key,
    ic_university varchar(25) not null comment '大学/企业名称',
    ic_country    varchar(50) not null comment '所属国家',
    ic_project    varchar(11) not null comment '合作项目id'
)
    comment '国际合作';

create table news
(
    news_id       int auto_increment comment '新闻id'
        primary key,
    news_provider varchar(11) default '0' null comment '新闻发布人id',
    news_title    varchar(50)             not null comment '新闻标题',
    news_desc     varchar(10000)          null comment '新闻描述',
    news_type     varchar(10)             null comment '新闻类型1教学科研2研究成果3会议记录',
    release_time  varchar(25)             null comment '发布时间'
)
    comment '新闻、通知';

create table project
(
    project_id    int auto_increment comment '项目id'
        primary key,
    project_name  varchar(20)  not null comment '项目名',
    project_owner varchar(11)  not null comment '项目所有人id',
    project_desc  varchar(200) null comment '项目描述'
)
    comment '项目信息表';

create table project_member
(
    pm_id              int auto_increment comment 'id'
        primary key,
    project_id         varchar(11) not null comment '项目id',
    project_member_id  varchar(11) not null comment '项目成员id',
    project_member_job varchar(20) null comment '项目成员职务'
)
    comment '项目成员表';

create table user
(
    user_id          int auto_increment comment '用户id'
        primary key,
    user_no          varchar(20) not null comment '用户编号',
    user_name        varchar(20) not null comment '用户姓名',
    user_psd         varchar(50) not null comment '用户密码',
    user_role        varchar(2)  not null comment '用户角色1管理员2教师3学生',
    user_level       varchar(5)  not null comment '学生层次',
    last_login_time  varchar(25) null comment '上次登入时间',
    last_logout_time varchar(25) null comment '上次登出时间'
)
    comment '用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '2015211801', 'admin', 'admin', '1', '本科', '2015-01-01 01:01:01', '2015-01-01 01:01:01');
INSERT INTO `user` VALUES (2, '2015211802', '123', '123', '2', '本科', '2015-01-01 01:01:01', '2015-01-01 01:01:01');
INSERT INTO `user` VALUES (3, '2015211803', '124', '123', '3', '本科', '2015-01-01 01:01:01', '2015-01-01 01:01:01');
INSERT INTO `user` VALUES (4, '2015211804', '125', '123', '3', '本科', '2015-01-01 01:01:01', '2015-01-01 01:01:01');
INSERT INTO `user` VALUES (5, '2015211805', '126', '123', '3', '本科', '2015-01-01 01:01:01', '2015-01-01 01:01:01');
INSERT INTO `user` VALUES (6, '2015211806', '127', '123', '3', '本科', '2015-01-01 01:01:01', '2015-01-01 01:01:01');
INSERT INTO `user` VALUES (7, '2015212806', '1231', '123', '3', '本科', '2015-01-01 01:01:01', '2015-01-01 01:01:01');

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, '231212', '打野与反野', '游戏竞技', '本科', '2020-05-05 20:27:54', '1');
INSERT INTO `course` VALUES (2, '231215', '草丛三剑客', '游戏竞技', '本科', '2020-05-05 20:27:54', '1');
INSERT INTO `course` VALUES (3, '232212', '人与自然', '自然科学', '本科', '2020-05-05 20:27:54', '1');
INSERT INTO `course` VALUES (4, '331212', '太阳之子', '影视音乐', '本科', '2020-05-05 20:27:54', '1');
INSERT INTO `course` VALUES (5, '235412', '假面骑士RX', '影视音乐', '本科', '2020-05-05 20:27:54', '1');
INSERT INTO `course` VALUES (6, '234122', '假面骑士01', '影视音乐', '本科', '2020-05-05 20:27:54', '1');
INSERT INTO `course` VALUES (7, '231123', '假面骑士555', '影视音乐', '本科', '2020-05-05 20:27:54', '1');

-- ----------------------------
-- Records of international_cooperation
-- ----------------------------
INSERT INTO `international_cooperation` VALUES (1, '北京大学', 'China', '1');
INSERT INTO `international_cooperation` VALUES (2, '北京大学', 'China', '2');

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES (1, '1', '博大精深！习近平反复强调学习这位伟人', '（原标题：博大精深！习近平反复强调学习这位伟人）\r\n\r\n【编前语】5月5日是无产阶级的伟大导师马克思诞辰纪念日。马克思一生为人民解放而奋斗，给我们留下最有价值、最具影响力的精神财富——马克思主义。习近平总书记指出，马克思主义思想理论博大精深、常学常新。今天，新华社《学习进行时》与您一起梳理学习伟人马克思的经典话语。\r\n\r\n马克思是马克思主义政党的缔造者和国际共产主义的开创者，是近代以来最伟大的思想家。两个多世纪过去了，人类社会发生了巨大而深刻的变化，但马克思的名字依然在世界各地受到人们的尊敬，马克思的学说依然闪烁着耀眼的真理光芒！\r\n\r\n习近平总书记强调，马克思主义思想理论博大精深、常学常新。新时代，中国共产党人仍然要学习马克思，学习和实践马克思主义，不断从中汲取科学智慧和理论力量。今天，让我们一起重温马克思的经典话语，感悟真理的魅力。\r\n\r\n关于社会运动规律\r\n\r\n1.时间是人类发展的空间。\r\n\r\n2.哲学家们只是用不同的方式解释世界，而问题在于改变世界。\r\n\r\n3.人的本质并不是单个人所固有的抽象物。在其现实性上，它是一切社会关系的总和。\r\n\r\n4.“特殊的人格”的本质不是人的胡子、血液、抽象的肉体的本性，而是人的社会特质。\r\n\r\n5.社会的进步就是人类对美的追求的结晶。\r\n\r\n6.体力劳动是防止一切社会病毒的伟大的消毒剂。\r\n\r\n7.理论只要彻底，就能说服人。\r\n\r\n8.我们知道个人是微弱的，但是我们也知道整体就是力量。\r\n\r\n9.批判的武器当然不能代替武器的批判，物质力量只能用物质力量来摧毁；但是理论一经掌握群众，也会变成物质力量。\r\n\r\n10.没有无义务的权利，也没有无权利的义务。\r\n\r\n11.法官是法律世界的国王，除了法律就没有别的上司。\r\n\r\n12.一步实际行动比一打纲领更重要。\r\n\r\n　关于无产阶级革命\r\n\r\n1.共产党人的理论原理，决不是以这个或那个世界改革家所发明或发现的思想、原则为根据的。\r\n\r\n2.无产阶级专政的首要条件就是无产阶级军队。\r\n\r\n3.我们现在必须完全保持党的纪律，否则一切都会陷入淤泥中。\r\n\r\n4.无产者在这个革命中失去的只是锁链，他们获得的将是整个世界。\r\n\r\n5.全世界无产者，联合起来！\r\n\r\n关于科学\r\n\r\n1.任何时候，我也不会满足，越是多读书，就越是深刻地感到不满足，越感到自己知识贫乏。科学是奥妙无穷的。\r\n\r\n2.在科学的入口处，正像在地狱的入口处一样，必须提出这样的要求：这里必须根绝一切犹豫；这里任何怯懦都无济于事。\r\n\r\n3.在科学上没有平坦的大道，只有不畏劳苦沿着陡峭山路攀登的人，才有希望达到光辉的顶点。\r\n\r\n4.万事开头难，每门科学都是如此。\r\n\r\n5.科学绝不是一种自私自利的享乐。有幸能够致力于科学研究的人，首先应该拿自己的学识为人类服务。\r\n\r\n关于个人修养\r\n\r\n1.与其用华丽的外衣装饰自己，不如用知识武装自己。\r\n\r\n2.一个人应该：活泼而守纪律，天真而不幼稚，勇敢而不鲁莽，倔强而有原则，热情而不冲动，乐观而不盲目。\r\n\r\n3.人只有为自己同时代的人完善，为他们的幸福而工作，他才能达到自身的完善。\r\n\r\n4.在选择职业时，我们应该遵循的主要指针是人类的幸福和我们自身的完美。\r\n\r\n5.生活就像海洋，只有意志坚强的人，才能到达彼岸。\r\n\r\n6.良心是由人的知识和全部生活方式来决定的。\r\n\r\n7.后悔过去，不如奋斗将来。\r\n\r\n8.青春的光辉，理想的钥匙，生命的意义，乃至人类的生存、发展，全包含在这两个字之中：奋斗!', '1', '2020-05-05 17:10:11');
INSERT INTO `news` VALUES (2, '1', '原油宝投资者：中行谈退2成保证金 具体明后天面谈', '新京报讯（记者 张姝欣）据多位投资者透露接到中行分行的电话，沟通原油宝赔偿方案，相关人员给出的方案是将退还20%的保证金，但也有投资者表示中行只电话沟通了面谈赔偿方案的时间，对于退还保证金一事并未过多提及。据多位投资者表示，中行方面要求投资者携身份证到开户行面谈，时间集中在明后两天。同时，多位投资者表示对该方案不接受，要求按照4月20日晚22点原油价格结算，也有投资者表示要求按美国正式测试负值的时间即4月15日的结算价来结算。', '2', '2020-05-05 16:46:41');
INSERT INTO `news` VALUES (3, '5', '阅文回应\"侵吞去世作家收益\"等六大争议：均为不实', '网易科技讯 5月5日消息，针对近日来的诸多争论，刚刚阅文方面进行了回应。\r\n\r\n阅文回应侵吞去世作家收益等六大争议：均为不实\r\n\r\n针对“全盘免费，创作已死”，阅文表示，巩固深化付费阅读是我们发展进化的根基。“全面免费”不可能，不现实，官方声明已辟谣。\r\n\r\n针对“新合同推出，知名作家们纷纷断更、烂尾”，阅文表示，合同于2019年9月推出，个别网络文学作者因个人事务、写作状态的调整等请假、偶尔断更是常态，经有心人搜集、总结后，得出“知名作者因故纷纷断更”的不实结论。\r\n\r\n部分自媒体文章以移花接木的形式，把作家完本和另一本书的单章谣言结合在一起发布，引发谣言。多数知名作家，例如“爱潜水的乌贼”等，已经明确在微博和起点站内辟谣。\r\n\r\n针对“侵吞去世作家收益”，阅文表示，三痴最后的作品是创世中文网签约，由QQ阅读首发，该作品QQ阅读平台一直在架。起点是这部作品的分发平台之一，此前因为内容审核的原因，包括这部作品在内的多部作品没有继续同步，目前已经通过内容审核，恢复同步。\r\n\r\n微信读书亦是这部作品的分发渠道之一，在微信读书上付费阅读。大家所看到的是此前微信读书曾针对新用户上线的免费福利，目前这个福利也已下线。\r\n\r\n此外，三痴的作品虽然是买断书，但稿费阅文始终在发放，由家人继承。三痴是阅文平台的优秀作家也是我们永远的朋友，请大家尊重逝者，理性发表意见。\r\n\r\n针对“作者没有著作权”，阅文回应，著作权分为著作权人身权和著作权财产权。著作权人身权是不可分割的人身权利，著作权财产权在经双方协商后，在自愿的情况下授予。\r\n\r\n针对“作者的所有社交帐号阅文都有权运营，且作者不得发布损害阅文形象和利益的内容”，阅文表示，只有在作家授权阅文运营，且有运营需要的情况下，阅文才会进行社交账号的协助运营。一切基础是建立在作家授权及维护作家利益的基础上的。\r\n\r\n针对“5.5日后台操纵作者更新时间、威胁断更”，阅文表示，阅文今日作品更新数据并未有异常波动。阅文没有采取任何包括修改时间、威胁断更后不推荐等外界谣传的运营措施。（彭丽慧）', '3', '2020-05-05 20:27:54');

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES (1, '有我无敌', '1', 'testasewrqwr');
INSERT INTO `project` VALUES (2, '帝路多白骨', '5', '撒打算碟子钱');
INSERT INTO `project` VALUES (3, '一见无始终成空', '1', 'as掉在线');

-- ----------------------------
-- Records of project_member
-- ----------------------------
INSERT INTO `project_member` VALUES (1, '1', '1', '项目经理');
INSERT INTO `project_member` VALUES (2, '1', '2', '质量工程师');
INSERT INTO `project_member` VALUES (3, '1', '3', '开发工程师');
INSERT INTO `project_member` VALUES (4, '1', '4', '前端工程师');
INSERT INTO `project_member` VALUES (5, '2', '5', '项目经理');
INSERT INTO `project_member` VALUES (6, '2', '2', '质量工程师');