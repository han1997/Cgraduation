INSERT INTO appInfo VALUES(1,'999999','虚拟应用','超级管理员',to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),0,NULL);
INSERT INTO clearCenter VALUES(1,'999999','9999','虚拟清算中心');
INSERT INTO organ VALUES(1,1,'9999','虚拟网点','',0,0);
INSERT INTO role VALUES(1,1,'虚拟管理员','',0);
INSERT INTO userInfo VALUES(1,1,'admin','超级管理员','21232F297A57A5A743894A0E4A801FC3',0,0);
INSERT INTO userRole VALUES(1,1);

insert into menu values (1, '系统管理', '', null, '', 0, '', 1, 'folder_Open.gif', 0);
insert into menu values (2, '应用管理', 'admin/appl_find.action', 1, '', 1, '', null, 'sys.png', 1);
insert into menu values (3, '网点维护', 'admin/orga_find.action', 1, '', 0, '', null, 'sys.png', 0);
insert into menu values (4, '角色管理', 'admin/role_find.action', 1, '', 0, '', null, 'sys.png', 0);
insert into menu values (5, '用户管理', 'admin/user_find.action', 1, '', 0, '', null, 'sys.png', 0);
insert into menu values (6, '权限配置', 'admin/role_findRole.action', 1, '', 0, '', null, 'sys.png', 0);
insert into menu values (7, '修改密码', 'admin/user_toUpdatePass.action', 1, '', 0, '', null, 'sys.png', 0);
insert into menu values (8, '地区维护', 'admin/qszx_find.action', 1, '', 1, '', null, 'sys.png', 1);

insert into menu values (9, '交易管理', '', null, '', 0, '', 1, 'folder_Open.gif', 0);
insert into menu values (10, '账户余额查询', 'ycss/acct_toqueryBalance.action', 9, '', 0, '', null, 'sys.png', 0);
insert into menu values (21, '虚账户余额查询', 'ycss/acct_toBalanceQuery.action', 9, '', 0, '', null, 'sys.png', 0);
insert into menu values (11, '账户交易明细查询', 'ycss/acct_toqueryTrade.action', 9, '', 0, '', null, 'sys.png', 0);
insert into menu values (12, '密钥维护', 'ycss/syscontext_find.action', 9, '', 0, '', null, 'sys.png', 0);

insert into menu values (13, '信息管理', '', null, '', 0, '', 0, 'folder_Open.gif', 0);
insert into menu values (14, '代发账户查询', 'ycss/acct_find.action', 13, '', 0, '', null, 'sys.png', 0);
insert into menu values (15, '卡bin信息查询', 'ycss/card_find.action', 13, '', 0, '', null, 'sys.png', 0);
insert into menu values (16, '行号查询', 'ycss/unit_find.action', 13, '', 0, '', null, 'sys.png', 0);
insert into menu values (17, '批次查询', 'ycss/batch_find.action', 13, '', 0, '', null, 'sys.png', 0);
insert into menu values (18, '处理批次信息查询', 'ycss/dealBatch_find.action', 13, '', 0, '', null, 'sys.png', 0);
insert into menu values (19, '流水信息查询', 'ycss/trade_find.action', 13, '', 0, '', null, 'sys.png', 0);
insert into menu values (20, '批次处理结果上传', 'ycss/dealBatch_toupload.action', 13, '', 0, '', null, 'sys.png', 0);
insert into menu values (22, '短信任务手工处理', 'ycss/message_toMessageHand.action', 13, '', 0, '', null, 'sys.png', 0);
insert into menu values (23, '短信查询', 'ycss/message_find.action', 13, '', 0, '', null, 'sys.png', 0);
insert into menu values (24, '收件人维护', 'ycss/recver_find.action', 13, '', 0, '', null, 'sys.png', 0);
insert into menu values (25, '代发流水差异', 'ycss/trade_todiff.action', 13, '', 0, '', null, 'sys.png', 0);
INSERT INTO menu VALUES(26,'数据维护','web/ycss/data.jsp',13,NULL,0,NULL,NULL,'sys.png',0);
INSERT INTO menu VALUES(27,'发放账户余额查询','web/ycss/balance.jsp',13,NULL,0,NULL,NULL,'sys.png',0);



INSERT INTO application VALUES(1,5,'A000001','UserInfoAction','toadd','增加用户',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(2,5,'A000002','UserInfoAction','delete','删除用户',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(3,5,'A000003','UserInfoAction','toupdate','编辑用户',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(4,5,'A000004','UserInfoAction','deleteBatch','批量删除',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(5,3,'A000007','OrganAction','toadd','增加网点',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(6,3,'A000008','OrganAction','delete','删除网点',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(7,3,'A000009','OrganAction','toupdate','编辑网点',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(8,3,'A000010','OrganAction','deleteBatch','批量删除',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(9,2,'A000011','AppInfoAction','toadd','增加应用',NULL,'ico1.gif',0,1);
INSERT INTO application VALUES(10,2,'A000012','AppInfoAction','delete','删除应用',NULL,'ico1.gif',0,1);
INSERT INTO application VALUES(11,2,'A000013','AppInfoAction','toupdate','编辑应用',NULL,'ico1.gif',0,1);
INSERT INTO application VALUES(12,2,'A000014','AppInfoAction','deleteBatch','批量删除',NULL,'ico1.gif',0,1);
INSERT INTO application VALUES(13,4,'A000018','RoleAction','toadd','增加角色',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(14,4,'A000019','RoleAction','delete','删除角色',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(15,4,'A000020','RoleAction','toupdate','编辑角色',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(16,4,'A000021','RoleAction','deleteBatch','批量删除',NULL,'ico1.gif',0,0);

INSERT INTO application VALUES(17,17,'A100001','BatchInfoAction','entryIncom','确认入账',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(18,17,'A100002','BatchInfoAction','deal_file','批量文件生成',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(19,17,'A100003','DealBatchInfoAction','batch','代收付处理',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(20,17,'A100004','BatchInfoAction','endDeal','结束处理',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(21,17,'A100005','BatchInfoAction','refund','退付处理',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(22,17,'A100006','BatchInfoAction','confirm','退付状态确认',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(23,17,'A100007','BatchInfoAction','backfile','反馈',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(24,17,'A100008','BatchInfoAction','planpay','计划发放',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(25,17,'A100009','BatchInfoAction','updatestate','文件状态调整',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(26,17,'A100011','BatchInfoAction','failback','失败退回',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(27,18,'A100010','DealBatchInfoAction','updatestate','处理批次状态调整',NULL,'ico1.gif',0,0);

INSERT INTO virtualUserInfo VALUES(1,'3301','93301001',14,'f','',1,100,'20150925','','','','');
INSERT INTO ycss_sysContext(id,location,port,signStr,pwdStr,projCode,certLocation,pwdVersion,storeLocation,updateTime,getType,state,retCode,retMsg,feeCode,transType,timestamp,alias,storePassword) VALUES ('1','10.200.161.84',8881,'admin','97129636','58018018','/usr/java/server_V2.0.p12','1.0','/usr/java/server_V2.0.p12','2015-08-27 17:05:52',0,0,'','','09001','1','1440612000093','{06519ac4-023d-4f2c-b240-3a19037627cc}','1');

