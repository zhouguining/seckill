-- 秒杀执行存储过程 seckillId -long  userPhone String md5 string
DELIMITER $$ -- 更改结束符
-- 定义存储过程
-- 参数 in 入参 out 出参
-- ROW_COUNT()函数是返回上一条修改类型sql受影响的行数
CREATE PROCEDURE execute_seckill(IN v_seckill_id INT, IN v_phone VARCHAR(255), IN v_kill_time TIMESTAMP, OUT r_result INT)
	COMMENT '执行秒杀'
	BEGIN
		DECLARE insert_count  INT DEFAULT 0;
		
		START TRANSACTION;

		-- create_time设置为了默认当前时间
		INSERT IGNORE INTO success_seckill (seckill_id, user_phone)
		VALUES (v_seckill_id, v_phone);
		
		SELECT ROW_COUNT() INTO insert_count;

		-- 视频小于0好像不对.... -1 重复秒杀 
		IF(insert_count = 0) THEN
			ROLLBACK;
			SET r_result = -1;
		ELSEIF(insert_count < 0) THEN
			ROLLBACK;
			SET r_result = -2;
		ELSE
			UPDATE seckill
			SET number = number - 1
			WHERE seckill_id = v_seckill_id
				AND end_time  >  v_kill_time
				AND	start_time < v_kill_time
				AND number > 0;
			SELECT ROW_COUNT() INTO insert_count;
			IF(insert_count = 0) THEN
				ROLLBACK;
				SET r_result = 0;
			ELSEIF(insert_count  < 0) THEN
				ROLLBACK;
				SET r_result = -2;
			ELSE
				COMMIT;
				SET r_result = 1;
			END IF;
		END IF;
	END $$

DELIMITER ; -- 还原结束符


-- 在mysql console中测试存储过程
SET @r_result = -3; -- 定义返回变量

CALL execute_seckill(1003, '17783754872', NOW(), @r_result); -- 调用存储过程

SELECT @r_result; -- 获取结果

SHOW PROCEDURE STATUS LIKE '%seckill%'; -- 查看execute_seckill存储过程

DROP PROCEDURE execute_seckill; -- 删除存储过程

-- 存储过程
-- 1：存储过程优化-->事务行级锁持有的时间
-- 2：不要过度依赖存储过程,尽量不要用存储过程的逻辑去代替代码的逻辑，除非是银行等安全级别要求高的程序，不能向外透露存储业务逻辑
-- 3：简单的逻辑可以应用存储过程
-- 4：QPS：一个秒杀单接近6000/qps