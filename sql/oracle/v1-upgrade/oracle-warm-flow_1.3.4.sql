update FLOW_NODE set PERMISSION_FLAG =  REPLACE(PERMISSION_FLAG,'@@default@@|','');
update FLOW_NODE set PERMISSION_FLAG =  REPLACE(PERMISSION_FLAG,'@@spel@@|','');

UPDATE FLOW_NODE SET PERMISSION_FLAG = REPLACE(PERMISSION_FLAG, '@@default@@|', '');
UPDATE FLOW_NODE SET PERMISSION_FLAG = REPLACE(PERMISSION_FLAG, '@@spel@@|', '');
