drop sequence APPINFO_SEQ;
drop sequence APPLICATION_SEQ;
drop sequence MENU_SEQ;
drop sequence ORGAN_SEQ;
drop sequence PARAMETERS_SEQ;
drop sequence PRIVILEGE_SEQ;
drop sequence ROLE_SEQ;
drop sequence USERINFO_SEQ;
drop sequence CLEARCENTER_SEQ;
drop sequence VIRTUALUSERINFO_SEQ;
drop sequence LOGINFO_SEQ;

drop sequence SYSCONTEXT_SEQ;
drop sequence ACCTINFOINFO_SEQ;
drop sequence ACCTBALACEINFO_SEQ;
drop sequence CARDBIN_SEQ;
drop sequence UNITINFO_SEQ;
drop sequence BATCHINFO_SEQ;
drop sequence DEALBATCHINFO_SEQ;
drop sequence TRADE_SEQ;
drop sequence MESSAGEINFO_SEQ;
drop sequence RECVERINFO_SEQ;

create sequence APPINFO_SEQ minvalue 2 maxvalue 2147483647 start with 2 increment by 1 cache 2 order;
create sequence APPLICATION_SEQ minvalue 1 maxvalue 2147483647 start with 1 increment by 1 cache 2 order;
create sequence MENU_SEQ minvalue 1 maxvalue 2147483647 start with 1 increment by 1 cache 2 order;
create sequence ORGAN_SEQ minvalue 2 maxvalue 2147483647 start with 2 increment by 1 cache 2 order;
create sequence PARAMETERS_SEQ minvalue 1 maxvalue 2147483647 start with 1 increment by 1 cache 2 order;
create sequence PRIVILEGE_SEQ minvalue 1 maxvalue 2147483647 start with 1 increment by 1 cache 2 order;
create sequence ROLE_SEQ minvalue 2 maxvalue 2147483647 start with 2 increment by 1 cache 2 order;
create sequence USERINFO_SEQ minvalue 2 maxvalue 2147483647 start with 2 increment by 1 cache 2 order;
create sequence CLEARCENTER_SEQ minvalue 2 maxvalue 2147483647 start with 2 increment by 1 cache 2 order;
create sequence VIRTUALUSERINFO_SEQ minvalue 1 maxvalue 2147483647 start with 1 increment by 1 cache 2 order;
create sequence LOGINFO_SEQ minvalue 1 maxvalue 2147483647 start with 1 increment by 1 cache 2 order;

create sequence SYSCONTEXT_SEQ minvalue 2 maxvalue 2147483647 start with 1 increment by 1 cache 2 order;
create sequence ACCTINFOINFO_SEQ minvalue 1 maxvalue 2147483647 start with 1 increment by 1 cache 2 order;
create sequence ACCTBALANCEINFO_SEQ minvalue 1 maxvalue 2147483647 start with 1 increment by 1 cache 2 order;
create sequence CARDBIN_SEQ minvalue 1 maxvalue 2147483647 start with 1 increment by 1 cache 2 order;
create sequence UNITINFO_SEQ minvalue 1 maxvalue 2147483647 start with 1 increment by 1 cache 2 order;
create sequence BATCHINFO_SEQ minvalue 1 maxvalue 2147483647 start with 1 increment by 1 cache 2 order;
create sequence DEALBATCHINFO_SEQ minvalue 1 maxvalue 2147483647 start with 1 increment by 1 cache 2 order;
create sequence TRADE_SEQ minvalue 1 maxvalue 2147483647 start with 1 increment by 1 cache 2 order;
create sequence MESSAGEINFO_SEQ minvalue 1 maxvalue 2147483647 start with 1 increment by 1 cache 2 order;
create sequence RECVERINFO_SEQ minvalue 1 maxvalue 2147483647 start with 1 increment by 1 cache 2 order;


create sequence APPOINTDEALBATCHINFO_SEQ minvalue 1 maxvalue 2147483647 start with 1 increment by 1 cache 2 order;