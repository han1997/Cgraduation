INSERT INTO appInfo VALUES(1,'999999','����Ӧ��','��������Ա',to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),0,NULL);
INSERT INTO clearCenter VALUES(1,'999999','9999','������������');
INSERT INTO organ VALUES(1,1,'9999','��������','',0,0);
INSERT INTO role VALUES(1,1,'�������Ա','',0);
INSERT INTO userInfo VALUES(1,1,'admin','��������Ա','21232F297A57A5A743894A0E4A801FC3',0,0);
INSERT INTO userRole VALUES(1,1);

insert into menu values (1, 'ϵͳ����', '', null, '', 0, '', 1, 'folder_Open.gif', 0);
insert into menu values (2, 'Ӧ�ù���', 'admin/appl_find.action', 1, '', 1, '', null, 'sys.png', 1);
insert into menu values (3, '����ά��', 'admin/orga_find.action', 1, '', 0, '', null, 'sys.png', 0);
insert into menu values (4, '��ɫ����', 'admin/role_find.action', 1, '', 0, '', null, 'sys.png', 0);
insert into menu values (5, '�û�����', 'admin/user_find.action', 1, '', 0, '', null, 'sys.png', 0);
insert into menu values (6, 'Ȩ������', 'admin/role_findRole.action', 1, '', 0, '', null, 'sys.png', 0);
insert into menu values (7, '�޸�����', 'admin/user_toUpdatePass.action', 1, '', 0, '', null, 'sys.png', 0);
insert into menu values (8, '����ά��', 'admin/qszx_find.action', 1, '', 1, '', null, 'sys.png', 1);

insert into menu values (9, '���׹���', '', null, '', 0, '', 1, 'folder_Open.gif', 0);
insert into menu values (10, '�˻�����ѯ', 'ycss/acct_toqueryBalance.action', 9, '', 0, '', null, 'sys.png', 0);
insert into menu values (21, '���˻�����ѯ', 'ycss/acct_toBalanceQuery.action', 9, '', 0, '', null, 'sys.png', 0);
insert into menu values (11, '�˻�������ϸ��ѯ', 'ycss/acct_toqueryTrade.action', 9, '', 0, '', null, 'sys.png', 0);
insert into menu values (12, '��Կά��', 'ycss/syscontext_find.action', 9, '', 0, '', null, 'sys.png', 0);

insert into menu values (13, '��Ϣ����', '', null, '', 0, '', 0, 'folder_Open.gif', 0);
insert into menu values (14, '�����˻���ѯ', 'ycss/acct_find.action', 13, '', 0, '', null, 'sys.png', 0);
insert into menu values (15, '��bin��Ϣ��ѯ', 'ycss/card_find.action', 13, '', 0, '', null, 'sys.png', 0);
insert into menu values (16, '�кŲ�ѯ', 'ycss/unit_find.action', 13, '', 0, '', null, 'sys.png', 0);
insert into menu values (17, '���β�ѯ', 'ycss/batch_find.action', 13, '', 0, '', null, 'sys.png', 0);
insert into menu values (18, '����������Ϣ��ѯ', 'ycss/dealBatch_find.action', 13, '', 0, '', null, 'sys.png', 0);
insert into menu values (19, '��ˮ��Ϣ��ѯ', 'ycss/trade_find.action', 13, '', 0, '', null, 'sys.png', 0);
insert into menu values (20, '���δ������ϴ�', 'ycss/dealBatch_toupload.action', 13, '', 0, '', null, 'sys.png', 0);
insert into menu values (22, '���������ֹ�����', 'ycss/message_toMessageHand.action', 13, '', 0, '', null, 'sys.png', 0);
insert into menu values (23, '���Ų�ѯ', 'ycss/message_find.action', 13, '', 0, '', null, 'sys.png', 0);
insert into menu values (24, '�ռ���ά��', 'ycss/recver_find.action', 13, '', 0, '', null, 'sys.png', 0);
insert into menu values (25, '������ˮ����', 'ycss/trade_todiff.action', 13, '', 0, '', null, 'sys.png', 0);
INSERT INTO menu VALUES(26,'����ά��','web/ycss/data.jsp',13,NULL,0,NULL,NULL,'sys.png',0);
INSERT INTO menu VALUES(27,'�����˻�����ѯ','web/ycss/balance.jsp',13,NULL,0,NULL,NULL,'sys.png',0);



INSERT INTO application VALUES(1,5,'A000001','UserInfoAction','toadd','�����û�',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(2,5,'A000002','UserInfoAction','delete','ɾ���û�',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(3,5,'A000003','UserInfoAction','toupdate','�༭�û�',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(4,5,'A000004','UserInfoAction','deleteBatch','����ɾ��',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(5,3,'A000007','OrganAction','toadd','��������',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(6,3,'A000008','OrganAction','delete','ɾ������',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(7,3,'A000009','OrganAction','toupdate','�༭����',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(8,3,'A000010','OrganAction','deleteBatch','����ɾ��',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(9,2,'A000011','AppInfoAction','toadd','����Ӧ��',NULL,'ico1.gif',0,1);
INSERT INTO application VALUES(10,2,'A000012','AppInfoAction','delete','ɾ��Ӧ��',NULL,'ico1.gif',0,1);
INSERT INTO application VALUES(11,2,'A000013','AppInfoAction','toupdate','�༭Ӧ��',NULL,'ico1.gif',0,1);
INSERT INTO application VALUES(12,2,'A000014','AppInfoAction','deleteBatch','����ɾ��',NULL,'ico1.gif',0,1);
INSERT INTO application VALUES(13,4,'A000018','RoleAction','toadd','���ӽ�ɫ',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(14,4,'A000019','RoleAction','delete','ɾ����ɫ',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(15,4,'A000020','RoleAction','toupdate','�༭��ɫ',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(16,4,'A000021','RoleAction','deleteBatch','����ɾ��',NULL,'ico1.gif',0,0);

INSERT INTO application VALUES(17,17,'A100001','BatchInfoAction','entryIncom','ȷ������',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(18,17,'A100002','BatchInfoAction','deal_file','�����ļ�����',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(19,17,'A100003','DealBatchInfoAction','batch','���ո�����',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(20,17,'A100004','BatchInfoAction','endDeal','��������',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(21,17,'A100005','BatchInfoAction','refund','�˸�����',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(22,17,'A100006','BatchInfoAction','confirm','�˸�״̬ȷ��',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(23,17,'A100007','BatchInfoAction','backfile','����',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(24,17,'A100008','BatchInfoAction','planpay','�ƻ�����',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(25,17,'A100009','BatchInfoAction','updatestate','�ļ�״̬����',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(26,17,'A100011','BatchInfoAction','failback','ʧ���˻�',NULL,'ico1.gif',0,0);
INSERT INTO application VALUES(27,18,'A100010','DealBatchInfoAction','updatestate','��������״̬����',NULL,'ico1.gif',0,0);

INSERT INTO virtualUserInfo VALUES(1,'3301','93301001',14,'f','',1,100,'20150925','','','','');
INSERT INTO ycss_sysContext(id,location,port,signStr,pwdStr,projCode,certLocation,pwdVersion,storeLocation,updateTime,getType,state,retCode,retMsg,feeCode,transType,timestamp,alias,storePassword) VALUES ('1','10.200.161.84',8881,'admin','97129636','58018018','/usr/java/server_V2.0.p12','1.0','/usr/java/server_V2.0.p12','2015-08-27 17:05:52',0,0,'','','09001','1','1440612000093','{06519ac4-023d-4f2c-b240-3a19037627cc}','1');

