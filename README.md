# softwaredevelopment
Verkefni í Þróun hugbúnaðar HÍ

CREATE TABLE public.booking
(
  id integer NOT NULL DEFAULT nextval('"Booking_id_seq"'::regclass),
  "hotelId" integer NOT NULL DEFAULT nextval('"Booking_hotelId_seq"'::regclass),
  "roomId" integer NOT NULL DEFAULT nextval('"Booking_roomId_seq"'::regclass),
  "phoneNumber" text,
  "costumerName" text,
  email text,
  "creditCardNumber" text,
  "startDate" date,
  "endDate" date,
  CONSTRAINT "Booking_pkey" PRIMARY KEY (id),
  CONSTRAINT "Booking_hotelId_fkey" FOREIGN KEY ("hotelId")
      REFERENCES public."Hotel" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "Booking_roomId_fkey" FOREIGN KEY ("roomId")
      REFERENCES public."Room" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)

CREATE TABLE public.hotel
(
  name text,
  address text,
  type text,
  description text,
  "phoneNumber" text,
  "starCount" double precision,
  rating double precision,
  "numberOfRooms" integer,
  "avgPrice" double precision,
  "checkOutTime" date,
  id integer NOT NULL DEFAULT nextval('"Hotel_hotelId_seq"'::regclass),
  tags text[],
  CONSTRAINT "Hotel_pkey" PRIMARY KEY (id)
)

CREATE TABLE public.review
(
  id integer NOT NULL DEFAULT nextval('"Review_id_seq"'::regclass),
  "hotelId" integer NOT NULL DEFAULT nextval('"Review_hotelId_seq"'::regclass),
  "user" text,
  date date,
  "helpCount" integer,
  review text,
  "userRating" double precision,
  CONSTRAINT "Review_pkey" PRIMARY KEY (id),
  CONSTRAINT "Review_hotelId_fkey" FOREIGN KEY ("hotelId")
      REFERENCES public.hotel (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)

CREATE TABLE public.room
(
  id integer NOT NULL DEFAULT nextval('"Room_roomId_seq"'::regclass),
  "hotelId" integer NOT NULL DEFAULT nextval('"Room_hotelId_seq"'::regclass),
  "numberOfBeds" integer,
  "sizeOfRoom" double precision,
  "typeOfBathroom" text,
  "roomNumber" integer,
  "maxGuests" integer,
  description text,
  "roomPrice" double precision,
  CONSTRAINT "Room_pkey" PRIMARY KEY (id),
  CONSTRAINT "Room_hotelId_fkey" FOREIGN KEY ("hotelId")
      REFERENCES public.hotel (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)

