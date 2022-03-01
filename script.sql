--
-- NOTE:
--
-- File paths need to be edited. Search for $$PATH$$ and
-- replace it with the path to the directory containing
-- the extracted data files.
--
--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

ALTER TABLE ONLY public.talle DROP CONSTRAINT fk_talle_tipo_articulo;
ALTER TABLE ONLY public.talle_articulos DROP CONSTRAINT fk_talle_articulos_talle;
ALTER TABLE ONLY public.talle_articulos DROP CONSTRAINT fk_talle_articulos_articulos;
ALTER TABLE ONLY public.pagos DROP CONSTRAINT fk_pagos_factura;
ALTER TABLE ONLY public.facturas DROP CONSTRAINT fk_facturas_mclientes;
ALTER TABLE ONLY public.fa DROP CONSTRAINT fk_factura_articulo_talle;
ALTER TABLE ONLY public.fa DROP CONSTRAINT fk_factura_articulo_factura;
ALTER TABLE ONLY public.fa DROP CONSTRAINT fk_factura_articulo_articulo;
ALTER TABLE ONLY public.articulos DROP CONSTRAINT fk_articulos_tipo;
ALTER TABLE ONLY public.pagos DROP CONSTRAINT pk_pagos;
ALTER TABLE ONLY public.misclientes DROP CONSTRAINT pk_mcliente;
ALTER TABLE ONLY public.usuarios DROP CONSTRAINT pk_id_user;
ALTER TABLE ONLY public.tipo_articulo DROP CONSTRAINT pk_id_tipo;
ALTER TABLE ONLY public.fa DROP CONSTRAINT pk_factura_articulo;
ALTER TABLE ONLY public.facturas DROP CONSTRAINT pk_factura;
ALTER TABLE ONLY public.articulos DROP CONSTRAINT pk_articulo;
ALTER TABLE ONLY public.misclientes DROP CONSTRAINT misclientes_nombre_key;
ALTER TABLE ONLY public.talle DROP CONSTRAINT id_talle;
ALTER TABLE public.usuarios ALTER COLUMN id_user DROP DEFAULT;
ALTER TABLE public.tipo_articulo ALTER COLUMN id_tipo DROP DEFAULT;
ALTER TABLE public.talle ALTER COLUMN id_talle DROP DEFAULT;
ALTER TABLE public.pagos ALTER COLUMN id_pago DROP DEFAULT;
ALTER TABLE public.misclientes ALTER COLUMN id_mcliente DROP DEFAULT;
ALTER TABLE public.facturas ALTER COLUMN id_factura DROP DEFAULT;
ALTER TABLE public.articulos ALTER COLUMN id_articulo DROP DEFAULT;
DROP SEQUENCE public.usuarios_id_user_seq;
DROP TABLE public.usuarios;
DROP SEQUENCE public.tipo_articulo_id_tipo_seq;
DROP TABLE public.tipo_articulo;
DROP SEQUENCE public.talle_id_talle_seq;
DROP TABLE public.talle_articulos;
DROP TABLE public.talle;
DROP SEQUENCE public.pagos_id_pago_seq;
DROP TABLE public.pagos;
DROP SEQUENCE public.misclientes_id_mcliente_seq;
DROP TABLE public.misclientes;
DROP SEQUENCE public.facturas_id_factura_seq;
DROP TABLE public.facturas;
DROP TABLE public.fa;
DROP SEQUENCE public.articulos_id_articulo_seq;
DROP TABLE public.articulos;
DROP DOMAIN public.dom_precio;
DROP DOMAIN public.dom_pk_num;
DROP DOMAIN public.dom_nombres;
DROP DOMAIN public.dom_fecha;
DROP DOMAIN public.dom_enteros;
DROP DOMAIN public.dom_eliminado;
DROP DOMAIN public.dom_descripcion;
DROP EXTENSION plpgsql;
DROP SCHEMA public;
--
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- Name: dom_descripcion; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN dom_descripcion AS character varying(50);


ALTER DOMAIN public.dom_descripcion OWNER TO postgres;

--
-- Name: dom_eliminado; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN dom_eliminado AS character(1) NOT NULL DEFAULT '0'::bpchar
	CONSTRAINT dom_eliminado_check CHECK ((VALUE = ANY (ARRAY['0'::bpchar, '1'::bpchar])));


ALTER DOMAIN public.dom_eliminado OWNER TO postgres;

--
-- Name: dom_enteros; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN dom_enteros AS integer
	CONSTRAINT dom_enteros_check CHECK ((VALUE >= 0));


ALTER DOMAIN public.dom_enteros OWNER TO postgres;

--
-- Name: dom_fecha; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN dom_fecha AS date NOT NULL DEFAULT '2020-06-27'::date;


ALTER DOMAIN public.dom_fecha OWNER TO postgres;

--
-- Name: dom_nombres; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN dom_nombres AS character varying(50) NOT NULL;


ALTER DOMAIN public.dom_nombres OWNER TO postgres;

--
-- Name: dom_pk_num; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN dom_pk_num AS integer NOT NULL
	CONSTRAINT dom_pk_num_check CHECK ((VALUE > 0));


ALTER DOMAIN public.dom_pk_num OWNER TO postgres;

--
-- Name: dom_precio; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN dom_precio AS real NOT NULL DEFAULT 0::real;


ALTER DOMAIN public.dom_precio OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: articulos; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE articulos (
    id_articulo integer NOT NULL,
    nombre dom_nombres NOT NULL,
    marca dom_nombres,
    costo dom_precio NOT NULL,
    ganancia dom_precio NOT NULL,
    precio_venta dom_precio NOT NULL,
    precio_venta_tarjeta dom_precio NOT NULL,
    eliminado dom_eliminado,
    id_tipo integer
);


ALTER TABLE public.articulos OWNER TO postgres;

--
-- Name: articulos_id_articulo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE articulos_id_articulo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.articulos_id_articulo_seq OWNER TO postgres;

--
-- Name: articulos_id_articulo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE articulos_id_articulo_seq OWNED BY articulos.id_articulo;


--
-- Name: fa; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE fa (
    id_factura dom_pk_num NOT NULL,
    id_articulo dom_pk_num NOT NULL,
    cantidad dom_enteros NOT NULL,
    costo dom_precio NOT NULL,
    ganancia dom_precio NOT NULL,
    precio_venta dom_precio NOT NULL,
    precio_venta_tarjeta dom_precio NOT NULL,
    descuento dom_precio,
    motivo dom_descripcion,
    subtotal dom_precio NOT NULL,
    id_talle dom_pk_num NOT NULL
);


ALTER TABLE public.fa OWNER TO postgres;

--
-- Name: facturas; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE facturas (
    id_factura integer NOT NULL,
    fecha dom_fecha,
    forma_pago dom_descripcion NOT NULL,
    comprobante dom_descripcion NOT NULL,
    descuento dom_precio,
    motivo dom_descripcion,
    total dom_precio NOT NULL,
    estado dom_descripcion NOT NULL,
    entregado dom_precio NOT NULL,
    id_mcliente dom_pk_num,
    eliminado dom_eliminado,
    porcentaje_tarjeta dom_precio
);


ALTER TABLE public.facturas OWNER TO postgres;

--
-- Name: facturas_id_factura_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE facturas_id_factura_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.facturas_id_factura_seq OWNER TO postgres;

--
-- Name: facturas_id_factura_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE facturas_id_factura_seq OWNED BY facturas.id_factura;


--
-- Name: misclientes; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE misclientes (
    id_mcliente integer NOT NULL,
    nombre dom_nombres,
    telefono dom_descripcion,
    correo dom_descripcion,
    saldo_favor dom_precio,
    fecha_saldo dom_fecha,
    eliminado dom_eliminado
);


ALTER TABLE public.misclientes OWNER TO postgres;

--
-- Name: misclientes_id_mcliente_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE misclientes_id_mcliente_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.misclientes_id_mcliente_seq OWNER TO postgres;

--
-- Name: misclientes_id_mcliente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE misclientes_id_mcliente_seq OWNED BY misclientes.id_mcliente;


--
-- Name: pagos; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pagos (
    id_pago integer NOT NULL,
    id_factura dom_pk_num,
    fecha dom_fecha NOT NULL,
    entregado dom_precio NOT NULL,
    estado dom_descripcion NOT NULL
);


ALTER TABLE public.pagos OWNER TO postgres;

--
-- Name: pagos_id_pago_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pagos_id_pago_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pagos_id_pago_seq OWNER TO postgres;

--
-- Name: pagos_id_pago_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pagos_id_pago_seq OWNED BY pagos.id_pago;


--
-- Name: talle; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE talle (
    id_talle integer NOT NULL,
    nombre dom_nombres NOT NULL,
    id_tipo dom_pk_num NOT NULL
);


ALTER TABLE public.talle OWNER TO postgres;

--
-- Name: talle_articulos; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE talle_articulos (
    id_articulo dom_pk_num NOT NULL,
    id_talle dom_pk_num NOT NULL,
    stock dom_enteros NOT NULL
);


ALTER TABLE public.talle_articulos OWNER TO postgres;

--
-- Name: talle_id_talle_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE talle_id_talle_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.talle_id_talle_seq OWNER TO postgres;

--
-- Name: talle_id_talle_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE talle_id_talle_seq OWNED BY talle.id_talle;


--
-- Name: tipo_articulo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tipo_articulo (
    id_tipo integer NOT NULL,
    nombre dom_nombres NOT NULL
);


ALTER TABLE public.tipo_articulo OWNER TO postgres;

--
-- Name: tipo_articulo_id_tipo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tipo_articulo_id_tipo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tipo_articulo_id_tipo_seq OWNER TO postgres;

--
-- Name: tipo_articulo_id_tipo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tipo_articulo_id_tipo_seq OWNED BY tipo_articulo.id_tipo;


--
-- Name: usuarios; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE usuarios (
    id_user integer NOT NULL,
    nombre dom_nombres,
    password dom_nombres,
    rol dom_nombres,
    eliminado dom_eliminado
);


ALTER TABLE public.usuarios OWNER TO postgres;

--
-- Name: usuarios_id_user_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE usuarios_id_user_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuarios_id_user_seq OWNER TO postgres;

--
-- Name: usuarios_id_user_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE usuarios_id_user_seq OWNED BY usuarios.id_user;


--
-- Name: id_articulo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY articulos ALTER COLUMN id_articulo SET DEFAULT nextval('articulos_id_articulo_seq'::regclass);


--
-- Name: id_factura; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY facturas ALTER COLUMN id_factura SET DEFAULT nextval('facturas_id_factura_seq'::regclass);


--
-- Name: id_mcliente; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY misclientes ALTER COLUMN id_mcliente SET DEFAULT nextval('misclientes_id_mcliente_seq'::regclass);


--
-- Name: id_pago; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pagos ALTER COLUMN id_pago SET DEFAULT nextval('pagos_id_pago_seq'::regclass);


--
-- Name: id_talle; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY talle ALTER COLUMN id_talle SET DEFAULT nextval('talle_id_talle_seq'::regclass);


--
-- Name: id_tipo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipo_articulo ALTER COLUMN id_tipo SET DEFAULT nextval('tipo_articulo_id_tipo_seq'::regclass);


--
-- Name: id_user; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuarios ALTER COLUMN id_user SET DEFAULT nextval('usuarios_id_user_seq'::regclass);


--
-- Data for Name: articulos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY articulos (id_articulo, nombre, marca, costo, ganancia, precio_venta, precio_venta_tarjeta, eliminado, id_tipo) FROM stdin;
\.
COPY articulos (id_articulo, nombre, marca, costo, ganancia, precio_venta, precio_venta_tarjeta, eliminado, id_tipo) FROM '$$PATH$$/2031.dat';

--
-- Name: articulos_id_articulo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('articulos_id_articulo_seq', 5, true);


--
-- Data for Name: fa; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY fa (id_factura, id_articulo, cantidad, costo, ganancia, precio_venta, precio_venta_tarjeta, descuento, motivo, subtotal, id_talle) FROM stdin;
\.
COPY fa (id_factura, id_articulo, cantidad, costo, ganancia, precio_venta, precio_venta_tarjeta, descuento, motivo, subtotal, id_talle) FROM '$$PATH$$/2032.dat';

--
-- Data for Name: facturas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY facturas (id_factura, fecha, forma_pago, comprobante, descuento, motivo, total, estado, entregado, id_mcliente, eliminado, porcentaje_tarjeta) FROM stdin;
\.
COPY facturas (id_factura, fecha, forma_pago, comprobante, descuento, motivo, total, estado, entregado, id_mcliente, eliminado, porcentaje_tarjeta) FROM '$$PATH$$/2029.dat';

--
-- Name: facturas_id_factura_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('facturas_id_factura_seq', 29, true);


--
-- Data for Name: misclientes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY misclientes (id_mcliente, nombre, telefono, correo, saldo_favor, fecha_saldo, eliminado) FROM stdin;
\.
COPY misclientes (id_mcliente, nombre, telefono, correo, saldo_favor, fecha_saldo, eliminado) FROM '$$PATH$$/2027.dat';

--
-- Name: misclientes_id_mcliente_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('misclientes_id_mcliente_seq', 2, true);


--
-- Data for Name: pagos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY pagos (id_pago, id_factura, fecha, entregado, estado) FROM stdin;
\.
COPY pagos (id_pago, id_factura, fecha, entregado, estado) FROM '$$PATH$$/2034.dat';

--
-- Name: pagos_id_pago_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('pagos_id_pago_seq', 46, true);


--
-- Data for Name: talle; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY talle (id_talle, nombre, id_tipo) FROM stdin;
\.
COPY talle (id_talle, nombre, id_tipo) FROM '$$PATH$$/2038.dat';

--
-- Data for Name: talle_articulos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY talle_articulos (id_articulo, id_talle, stock) FROM stdin;
\.
COPY talle_articulos (id_articulo, id_talle, stock) FROM '$$PATH$$/2039.dat';

--
-- Name: talle_id_talle_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('talle_id_talle_seq', 25, true);


--
-- Data for Name: tipo_articulo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tipo_articulo (id_tipo, nombre) FROM stdin;
\.
COPY tipo_articulo (id_tipo, nombre) FROM '$$PATH$$/2036.dat';

--
-- Name: tipo_articulo_id_tipo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tipo_articulo_id_tipo_seq', 1, false);


--
-- Data for Name: usuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY usuarios (id_user, nombre, password, rol, eliminado) FROM stdin;
\.
COPY usuarios (id_user, nombre, password, rol, eliminado) FROM '$$PATH$$/2041.dat';

--
-- Name: usuarios_id_user_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('usuarios_id_user_seq', 2, true);


--
-- Name: id_talle; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY talle
    ADD CONSTRAINT id_talle PRIMARY KEY (id_talle);


--
-- Name: misclientes_nombre_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY misclientes
    ADD CONSTRAINT misclientes_nombre_key UNIQUE (nombre);


--
-- Name: pk_articulo; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY articulos
    ADD CONSTRAINT pk_articulo PRIMARY KEY (id_articulo);


--
-- Name: pk_factura; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY facturas
    ADD CONSTRAINT pk_factura PRIMARY KEY (id_factura);


--
-- Name: pk_factura_articulo; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY fa
    ADD CONSTRAINT pk_factura_articulo PRIMARY KEY (id_factura, id_articulo, id_talle);


--
-- Name: pk_id_tipo; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tipo_articulo
    ADD CONSTRAINT pk_id_tipo PRIMARY KEY (id_tipo);


--
-- Name: pk_id_user; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuarios
    ADD CONSTRAINT pk_id_user PRIMARY KEY (id_user);


--
-- Name: pk_mcliente; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY misclientes
    ADD CONSTRAINT pk_mcliente PRIMARY KEY (id_mcliente);


--
-- Name: pk_pagos; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pagos
    ADD CONSTRAINT pk_pagos PRIMARY KEY (id_pago);


--
-- Name: fk_articulos_tipo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY articulos
    ADD CONSTRAINT fk_articulos_tipo FOREIGN KEY (id_tipo) REFERENCES tipo_articulo(id_tipo);


--
-- Name: fk_factura_articulo_articulo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fa
    ADD CONSTRAINT fk_factura_articulo_articulo FOREIGN KEY (id_articulo) REFERENCES articulos(id_articulo);


--
-- Name: fk_factura_articulo_factura; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fa
    ADD CONSTRAINT fk_factura_articulo_factura FOREIGN KEY (id_factura) REFERENCES facturas(id_factura);


--
-- Name: fk_factura_articulo_talle; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fa
    ADD CONSTRAINT fk_factura_articulo_talle FOREIGN KEY (id_talle) REFERENCES talle(id_talle);


--
-- Name: fk_facturas_mclientes; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY facturas
    ADD CONSTRAINT fk_facturas_mclientes FOREIGN KEY (id_mcliente) REFERENCES misclientes(id_mcliente);


--
-- Name: fk_pagos_factura; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pagos
    ADD CONSTRAINT fk_pagos_factura FOREIGN KEY (id_factura) REFERENCES facturas(id_factura);


--
-- Name: fk_talle_articulos_articulos; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY talle_articulos
    ADD CONSTRAINT fk_talle_articulos_articulos FOREIGN KEY (id_articulo) REFERENCES articulos(id_articulo);


--
-- Name: fk_talle_articulos_talle; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY talle_articulos
    ADD CONSTRAINT fk_talle_articulos_talle FOREIGN KEY (id_talle) REFERENCES talle(id_talle);


--
-- Name: fk_talle_tipo_articulo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY talle
    ADD CONSTRAINT fk_talle_tipo_articulo FOREIGN KEY (id_tipo) REFERENCES tipo_articulo(id_tipo);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

