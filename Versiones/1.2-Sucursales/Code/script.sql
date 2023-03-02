CREATE TABLE public.sucursales
(
    id_sucursal integer NOT NULL,
    nombre dom_nombres NOT NULL,
	eliminado dom_eliminado NOT NULL,
    CONSTRAINT pk_sucursal PRIMARY KEY (id_sucursal)
);

ALTER TABLE IF EXISTS public.sucursales
    OWNER to postgres;


CREATE SEQUENCE sucursales_id_sucursal_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.sucursales_id_sucursal_seq OWNER TO postgres;
ALTER SEQUENCE sucursales_id_sucursal_seq OWNED BY sucursales.id_sucursal;
	
ALTER TABLE ONLY sucursales ALTER COLUMN id_sucursal SET DEFAULT nextval('sucursales_id_sucursal_seq'::regclass);

INSERT INTO public.sucursales( nombre, eliminado) VALUES ('Sucursal 1', 0);
INSERT INTO public.sucursales( nombre, eliminado) VALUES ('Sucursal 2', 0);

ALTER TABLE IF EXISTS public.sucursales
    ADD CONSTRAINT uq_nombre UNIQUE (nombre);
	
ALTER TABLE IF EXISTS public.talle_articulos
    ADD COLUMN id_sucursal dom_pk_num NOT NULL DEFAULT 1;
ALTER TABLE IF EXISTS public.talle_articulos
    ADD PRIMARY KEY (id_articulo, id_talle, id_sucursal);
ALTER TABLE IF EXISTS public.talle_articulos
    ADD CONSTRAINT fk_talle_articulos_sucursal FOREIGN KEY (id_sucursal)
    REFERENCES public.sucursales (id_sucursal) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;
	
ALTER TABLE IF EXISTS public.usuarios
    ADD COLUMN id_sucursal dom_pk_num NOT NULL DEFAULT 1;
ALTER TABLE IF EXISTS public.usuarios
    ADD CONSTRAINT fk_usuarios_sucursales FOREIGN KEY (id_sucursal)
    REFERENCES public.sucursales (id_sucursal) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;
	
ALTER TABLE IF EXISTS public.facturas
    ADD COLUMN id_sucursal dom_pk_num NOT NULL DEFAULT 1;
ALTER TABLE IF EXISTS public.facturas
    ADD CONSTRAINT fk_facturas_sucursales FOREIGN KEY (id_sucursal)
    REFERENCES public.sucursales (id_sucursal) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;	