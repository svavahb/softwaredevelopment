# softwaredevelopment
Verkefni í Þróun hugbúnaðar HÍ

-- Table: public.booking

-- DROP TABLE public.booking;

CREATE TABLE public.booking
(
  id serial,
  hotelid integer,
  roomid integer,
  phonenumber text,
  costumername text,
  email text,
  creditcardnumber text,
  startdate date,
  enddate date,
  CONSTRAINT booking_pkey PRIMARY KEY (id),
  CONSTRAINT booking_hotelid_fkey FOREIGN KEY (hotelid)
      REFERENCES public.hotel (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT booking_roomid_fkey FOREIGN KEY (roomid)
      REFERENCES public.room (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.booking
  OWNER TO postgres;

-- Table: public.hotel

-- DROP TABLE public.hotel;

CREATE TABLE public.hotel
(
  hotelname text,
  address text,
  typeofhotel text,
  description text,
  phonenumber text,
  starcount double precision,
  avgprice double precision,
  checkouttime text,
  id serial,
  tags text[],
  CONSTRAINT hotel_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.hotel
  OWNER TO postgres;

-- Table: public.review

-- DROP TABLE public.review;

CREATE TABLE public.review
(
  id serial,
  hotelid integer,
  username text,
  datewritten date,
  helpcount integer,
  review text,
  userrating double precision,
  CONSTRAINT review_pkey PRIMARY KEY (id),
  CONSTRAINT review_hotelid_fkey FOREIGN KEY (hotelid)
      REFERENCES public.hotel (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.review
  OWNER TO postgres;

-- Table: public.room

-- DROP TABLE public.room;

CREATE TABLE public.room
(
  id serial,
  hotelid integer,
  numberofbeds integer,
  sizeofroom double precision,
  typeofbathroom text,
  roomnumber integer,
  maxguests integer,
  description text,
  roomprice double precision,
  CONSTRAINT room_pkey PRIMARY KEY (id),
  CONSTRAINT room_hotelid_fkey FOREIGN KEY (hotelid)
      REFERENCES public.hotel (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.room
  OWNER TO postgres;




svava sökkar




  TO DO:
  * klára dbhelper
  * klára hotel
  * klára room
  * klára review
  * klára booking
  * klára hotelcontroller
  * klára bookingcontroller

