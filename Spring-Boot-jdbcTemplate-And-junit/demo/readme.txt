#postgresql 建立自增长主键
设置　id 数据类型为　serial


#junit restTemplate 测试
测试 Controller 时记得启动　Application
restTemplate 使用：　https://blog.csdn.net/u012702547/article/details/77917939


＃spring jdbcTemplate crud
参考：　https://blog.csdn.net/saytime/article/details/74783294


＃数据库结构
CREATE TABLE public.t_user
(
  id integer NOT NULL DEFAULT nextval('user_id_seq'::regclass),
  name character varying(20),
  age integer,
  CONSTRAINT user_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.t_user
  OWNER TO postgres;
