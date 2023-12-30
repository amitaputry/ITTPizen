package com.ta.ittpizen.domain.utils

import com.ta.ittpizen.domain.model.PostItem
import com.ta.ittpizen.domain.model.PostItemType

object DataPostItem {

    private val allPosts = mutableListOf(
        PostItem(
            id = "1",
            userId = "34",
            postType = PostItemType.ACHIEVEMENT,
            type = "Lecturer",
            name = "Novanda Alim Setya N..",
            date = "50 minutes ago",
            text = "Selamat untuk Mahasiswa ITTP telah mendapat juara tingkat nasional, semangat terus!!..",
            media = "https://s3-alpha-sig.figma.com/img/2a9f/1bdf/d3015b017ba565d29a918f18d114f2c8?Expires=1704672000&Signature=eJdd8BZFLGKekzE1ZT9ddpkIhgNAePygw1ccfsCnha8LvrYmYq4C0f9ZSYGadz3jHB6MU7GkOsRjJv1B2r7nIIwUxZGR0jaE5PvNg1FCt51bQFMdDKyCYAprUEV-0dk08RoNu-6osPRhzJ0M1hLh~L77Lz6zcZngF1bRT~4FUsZUsg4KnRvZ4jXVdcqNzME~PqOvuJV8n0l3R6RnyLQCVKp10-i1ZLl3nv0SgBeFu-TDuK8Y3EaasGw6o4ZxlwiwvFO-rxfrgIphVyjktdlcDJnjkdBQ7DR0qJkRYlN63CipK7R3eX1jGC6eGHxmsiDpi9H~eyG57OJNYq7Hda5YMw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
            like = 2,
            comment = 0
        ),
        PostItem(
            id = "2",
            userId = "2",
            postType = PostItemType.TWEET,
            type = "Student",
            name = "Abdul Hafiz Ramadan",
            date = "5 minutes ago",
            text = "Halo\n" +
                    "Nama ku Abdul, Salam kenal semua, saya dari Prodi Rekayasa perangkat lunak.\n" +
                    "Mari saling konek ya gess.",
            like = 0,
            comment = 0
        ),
        PostItem(
            id = "3",
            userId = "33",
            postType = PostItemType.SCHOLARSHIP,
            type = "Staff",
            name = "Kemahasiswaan ITTP",
            date = "60 minutes ago",
            text = "Semangat Pagi!\n" +
                    "Pengumuman Pembukaan Beasiswa KIP Non Biaya hidup. \n" +
                    "Daftar Sekarang!!",
            media = "https://s3-alpha-sig.figma.com/img/1537/dc27/fa28d94bae6585500e91a37d8ece76a2?Expires=1704672000&Signature=dIZ-xunFRtnWU2EEzqp~qbIAZ19oFtdtcIs8n2KQclaiRjOlmLRyO6grvES4CFDO5HKPrvkseAEYmpX8wexfUMgn-dBDvfqcckKkZnE-net5H9czaipMQ499HpfP3K6798xZCPTVzRs9EutiG9J753OcI4k5bEyf6PFZUMwzgZP5vKr1OPwYSUxpDgaphQA2Xgeo6XxVDb3nlraGY3pg3b4HVYEITTu0o2Ij1x~pZkMCMmYURU-usQxh-wKjKmrRTrIS7cPs7Z-h5n-i77QSVWRxwwCt1kCZyFd-5vrTJj6O-JVc0tdWlQSmGbFhVvZu4YyayiH8sMMWi3i1M8zjfA__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
            like = 10,
            comment = 0
        ),
        PostItem(
            id = "4",
            userId = "6",
            postType = PostItemType.TWEET,
            type = "Student",
            name = "Ahmad Nawawi",
            date = "2 hours ago",
            text = "Halo Temen-temen, saya Nawawi dari Prodi RPL 20. Mari saling konek.. Terima kasih",
            like = 10,
            comment = 0
        ),
        PostItem(
            id = "5",
            userId = "33",
            postType = PostItemType.EVENT,
            type = "Staff",
            name = "Kemahasiswaan ITTP",
            date = "2 hours ago",
            text = "Halo ITTPizen..\n" +
                    "Datang ke acara seru untuk kamu yang berminat mengikuti kompetisi tahunan PKM, yuk datang diacara seru kali ini.\n" +
                    "Catat tanggalnya Senin, 7 Agustus 2023. \n" +
                    "#ITTPGoestoPIMNAS",
            media = "https://s3-alpha-sig.figma.com/img/6f33/8377/238ced2ebf7ecd6824b5a5c8139fb5d8?Expires=1704672000&Signature=QcjJgo05nL64ybiG3wlTTCiOz7rt0jjsyQ70QfYdDu3eH3crcx9SbbFpkK6MVPmHuaCS3Z-xO0Tm~cmdW8sroDcVBx4PVhb7aopNxYIW97h5CM-I-7RpiNJ1LOVMOm~PPr4nYwpfimmqW6qeViPZOqOjDQO86fn9dWkczMUyMcEFYZZlYQu-lin9~-NXCTARBvA~7eyzPwpABRidIEFn~bX6LG3sfuatjXMFJ54rWyigBZYCbnV4o~LCqt8nPLoKI2zAzKdErO-tW6SFeV3cfRblSmRDgYrXy4MYbO7M8Rr6mEQ5zmOaPMD5KfPp8y2QIact0V6J23vIYcopUE~DXw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
            like = 10,
            comment = 0
        ),
        PostItem(
            id = "6",
            userId = "123",
            postType = PostItemType.EVENT,
            type = "Student",
            name = "GDSC IT Telkom Purwo..",
            date = "2 hours ago",
            text = "[TECH TALK #1] \n" +
                    "Come to join Tech Talk “Prospek Karier menjadi Android Developer di Industri Masa Depan.\n" +
                    "Join us RSVP NOW s.id/GDSCTechTalk1",
            media = "https://s3-alpha-sig.figma.com/img/1c92/a17f/fa4b362007afc7f263953daa6f1c527c?Expires=1704672000&Signature=NVtnWLjNIZW3AZvBdfhpnzwfciFO-Z-87r2Korf6ykb6XkiMt3WKZpu0~YBfAiMt3H7ucthyRqb5f45FEh5R8oEAILVhWOOoXpil5Ogbkh-oTZtjfJx-cHkGh1fJ-34x8Z1EhZNZDLeoNOe8ZFjWbA~oxGCrfXfKVBROwzRzYu0A7PP8K-i8S99zA61HSAEM1TizJsGwkKdBn6FRbrbAD78D7sGmbp8cqWjUg9vwTKheUUimKSpfhv~2zWzM7oUCqfYwHvLrHD4ZPbO2XM4rMPZgQl566Wd4EEPGS6eSg1PiWNSZHVCQNv0XRRSgpZWHLvYmkm1sE-qf28eBNIx~2g__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
            like = 2,
            comment = 0
        ),
        PostItem(
            id = "7",
            userId = "0",
            type = "Student",
            name = "Amita Putry Prasasti",
            date = "1 hours ago",
            text = "Haloo, salam kenal, mari saling koneksi temen-temen",
            like = 1,
            comment = 0
        ),
        PostItem(
            id = "8",
            userId = "17",
            type = "Lecturer",
            name = "Gita Fadila Fitriana",
            date = "1 hours ago",
            text = "Assalamu’alaikum, Semua.. Mari saling berkoneksi ya",
            like = 4,
            comment = 0
        ),
        PostItem(
            id = "9",
            userId = "17",
            type = "Lecturer",
            name = "Gita Fadila Fitriana",
            date = "1 hours ago",
            text = "Assalamu’alaikum, Semua.. Mari saling berkoneksi ya",
            like = 4,
            comment = 0
        ),
        PostItem(
            id = "10",
            userId = "5",
            type = "Student",
            name = "Ajib Syah Abad",
            date = "1 hours ago",
            text = "Gess ingpo kelas EXPL hari ini daring atau luring? pliss di jawa",
            like = 0,
            comment = 0
        ),
        PostItem(
            id = "11",
            userId = "1",
            type = "Student",
            name = "Daffa Rayhan Riadi",
            date = "15 hours ago",
            text = "Permisi gan.. di kampus hujan engga ya? thanks",
            like = 0,
            comment = 0
        ),
        PostItem(
            id = "12",
            userId = "3",
            type = "Student",
            name = "Afifatunni’mah",
            date = "10 hours ago",
            text = "Permisi, disini ada yang pernah ikut kepanitian enggak yaa??",
            like = 0,
            comment = 0
        ),
        PostItem(
            id = "13",
            userId = "33",
            postType = PostItemType.ACADEMIC,
            type = "Staff",
            name = "Kemahasiswaan ITTP",
            date = "50 hours ago",
            text = "[PENGUMUMAN]\n" +
                    "Seleksi Internal International ICT 2023!\n" +
                    "Hi ITTPizen, kabar yang ditunggu-tunggu telah tiba, selamat untuk para ITTPizen yang terpilih menjadi bagian dari program ICT 2023!!\n" +
                    "#ITTPBerprestasi",
            media = "https://s3-alpha-sig.figma.com/img/6f33/8377/238ced2ebf7ecd6824b5a5c8139fb5d8?Expires=1704672000&Signature=QcjJgo05nL64ybiG3wlTTCiOz7rt0jjsyQ70QfYdDu3eH3crcx9SbbFpkK6MVPmHuaCS3Z-xO0Tm~cmdW8sroDcVBx4PVhb7aopNxYIW97h5CM-I-7RpiNJ1LOVMOm~PPr4nYwpfimmqW6qeViPZOqOjDQO86fn9dWkczMUyMcEFYZZlYQu-lin9~-NXCTARBvA~7eyzPwpABRidIEFn~bX6LG3sfuatjXMFJ54rWyigBZYCbnV4o~LCqt8nPLoKI2zAzKdErO-tW6SFeV3cfRblSmRDgYrXy4MYbO7M8Rr6mEQ5zmOaPMD5KfPp8y2QIact0V6J23vIYcopUE~DXw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
            like = 10,
            comment = 0
        ),
        PostItem(
            id = "14",
            userId = "33",
            postType = PostItemType.ACADEMIC,
            type = "Staff",
            name = "Kemahasiswaan ITTP",
            date = "50 minutes ago",
            text = "TIMELINE OLIMPIADE\n" +
                    "Matematika dan Ilmu Pengetahuan",
            media = "https://s3-alpha-sig.figma.com/img/e45d/88b1/d63df86a77c1791842c226a6d5499344?Expires=1704672000&Signature=mnQb8X~~6lt8YJeOp16T4Wa5t~xXHEIhIcqrFSfHfer3SAwFO1DP9iLZdW~ojNDn0b6KpPkzNARFDooIHBJ4jD4K4dCrZxqbzRdEa0CMc~uTh84kcLaqGhK9HFrgIXih~0Yj9EVb40sPOgS9yD7JDYBKXLmt0TMrFQweDmEAZdu0CWNJ62AEjqLUbx2k-5nzqDHPXs1EpThQuU8vp2UPO6LFCq19~Z5OWA~OHqJ5jdeAkXMqH1gj25fb6wpo8TPP38Xpwp6~4Pf8~I2PICceMiXBVbzllWhw6TL5MlcCMfG0UzGq9P8J3ulN8FCmW~RGnAf3WsftAbPxTiddHXlvMA__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
            like = 0,
            comment = 0
        ),
        PostItem(
            id = "15",
            userId = "35",
            postType = PostItemType.ACADEMIC,
            type = "Staff",
            name = "Pusat Karir ITTP",
            date = "30 minutes ago",
            text = "TWERK Telkom Career Day\n" +
                    "Regist & Info 081231302364",
            media = "https://s3-alpha-sig.figma.com/img/87fb/30a4/8e277c56e7baa9d852dad4ddf797719d?Expires=1704672000&Signature=cIoT5liYvQ3zKCj7G4AZEVZGgAcFJz13OECMEmmyVnGA1gw4pLzQSR6RYGPnx7nZHXawxmKsCW5abhgVJqi5ieFBCkJ-TeGphWof-8c7vYnBohMImzks2Y830YpM2TocI2gHbDsSzQJZG7XjudKlBh6XWS15FBFxpPqgTULnxvCxKFvFIayteU3nYWd~UMsBBdc3nOig3-6USFJ-YOERkyG~SbvicZQjdlL8cgJeplnVoBruhks5v7chOXPHHEnEsAd4~fVvlRQq5t0aLDV4HS0MnWOXmN7WtfugwcL8IzwMcs-5GY5Cq5a4Q1jzEMHR7jcLd75hY6GrqkB0igmQwg__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
            like = 22,
            comment = 0
        ),
        PostItem(
            id = "16",
            userId = "35",
            postType = PostItemType.ACHIEVEMENT,
            type = "Staff",
            name = "Pusat Karir ITTP",
            date = "10 minutes ago",
            text = "Selamat IT Telkom Purwokerto Meraih Klaster Utama Perguruan Tinggi Kemendikbudristek 2023. \n" +
                    "#PrestasiITTP #ITTPJuara",
            media = "https://s3-alpha-sig.figma.com/img/0dd0/f2c8/64c8947555a140677b389e5b9ddf96ae?Expires=1704672000&Signature=qcLeA5sKiYNTtqHgODUcBdY06-oVdUkUZpSi3JB8DuIzH4iFrhs1pSyr7vhPIgj6mC4MqYX9aKuMa-ZFHI-hKK9S8PhjEOTx11yx1-uOKRONF8W5Gfm~zSmLwOwP-Zu79yurZxCNSXEcbXfDScMnn1WjfFbiDGiM5GiYXmk7WpAaDbKRZ1Hh9mhuLYgBbGhFz68vVDPC4nAh6sAoMreX~VDw9sofqSjXQy9Qde9xDstX~YHakjC4~P3Cg~~t32IZxxBWTtlYiZKFWxDFhJo8Pwy3uJG394tdg4SWFg6Bz-Q7YINaQ0Z5hVefUxxBvGk39ZBjSH7aMYT3E0EqC3a7Sg__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
            like = 1,
            comment = 0
        ),
        PostItem(
            id = "17",
            userId = "33",
            postType = PostItemType.ACHIEVEMENT,
            type = "Staff",
            name = "Kemahasiswaan ITTP",
            date = "50 minutes ago",
            text = "Selamat dan Sukses LLDIKTI VI Peraihan penghargaan Anugrah Diktiristek 2023. Selamat untuk bapak/ibu Dosen ITTP Terbaik..\n" +
                    "#PrestasiITTP",
            media = "https://s3-alpha-sig.figma.com/img/763a/f3c8/9b8f72dc8b512bf42c06301c71b9335f?Expires=1704672000&Signature=pTLLtU79F5kGcXZQxngECUKK0fXELCCnaHp0nBMhu4kP3yUm0L7wIN1YTOQy4FaXtT5ViaoToBtFM8KDeqTI7q4dM1WWLWM~g0NecLnZvSiJZlwPpEDnbUg61IaVtijvqyPbEnxaPNtdg70XnaB5gxD9AazT4dRZj9A-zdJ~ZwppKhAvGyogiAvhXsiKMeMfKy~8qkwA0Xs3Wim1AlDnZswEXwMS9WYR8BhYmn6Sc25Xr-DNJZOSrHgIGyg~9AIC-s4IpA1kgH2QB0nsCyskK8Coumf5w7VuQu1VAYHjEQ8-VC10PZ9eR4EiGe5yJhXb4xg~iwu5YhmVyQawPdsSog__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
            like = 1,
            comment = 0
        ),
        PostItem(
            id = "18",
            userId = "33",
            postType = PostItemType.ACHIEVEMENT,
            type = "Staff",
            name = "Kemahasiswaan ITTP",
            date = "50 minutes ago",
            text = "CONGRATULATIONS!\n" +
                    "2 Mahasiswa Teknik Industri berhasil mendapatkan Juara 2 Pada Sobat Competition 2023 Kategori Inovasi Umum yang diselenggarakan oleh PT United Tractors Tbk.\n" +
                    "#PrestasiITTP #ITTPJuara",
            media = "https://s3-alpha-sig.figma.com/img/754b/1465/85537f27457354bd17a5f2dd80a234b3?Expires=1704672000&Signature=jkz~uenW6WwRUCheSIR1j09X-Lm-PevR3yRFPIfLmfg744x3QJSv0AqJE6~9c4swKcFeIymvK1B1y7ix0ENSY2D8kgLioLYMQCWxdQNPDSICt1dFXzwfPpUxq40pmd94wYTyV8faHj16yoWjj6Sj~yGHonCFKm0r6-crJiH~cXfuINL7dX-XZNmy~EBQ8zbKe9fs~HbFoMtXysL15Au~KNZ5RpDxGol-Uo7rOuNziUfLdHinlO~0x5fQ9efbH5sWf6RRy2mLPG1pV4XOsgD6KpemOFV2fbAt-Mhe-pIxmUYwkSG0IMmxlIvntA5dcHliZIBFbnrCOUTgb9MeiCSIOg__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
            like = 1,
            comment = 0
        ),
        PostItem(
            id = "19",
            userId = "33",
            postType = PostItemType.ACHIEVEMENT,
            type = "Staff",
            name = "Kemahasiswaan ITTP",
            date = "50 minutes ago",
            text = "Lagi lagi Membanggakan!\n" +
                    "Mahasiswa Teknik Elektro berhasil meraih Juara Harapan 2 Pada Kompetisi National Sains Indonesia.\n" +
                    "#PrestasiITTP",
            media = "https://s3-alpha-sig.figma.com/img/90dd/5b3d/a696e498863311c7bef489cd716125a2?Expires=1704672000&Signature=Q~6pKf59jKYIKv~PXSEhB6E7fgFKiJuTDU26pgKBpKaSHG0-zokGqM7Wlwjql550v1NytfHtq0MEPiS~oxwou~eH~1TbQxddErUBPv-e-xUmxGmtnRf2wxK0qS7wtM1UIXNF7pQZxhPut38iGHl40cfc-EAELHSluOGf2~ww5ipZYHVdD8z00uiCEL~MEYx-0upIW7ime2Lef6uZksFsMLz54vTT2Zq0K-1muA~h-huBda9AuPikRoKhGtJlIzUzkpiSrrUj~uNuxkyVyPM8MH9zIrjJKgDW~4gBjoTEd0jloy21H4Y7H~oDElLtIMgxxdF1Q2Xc-l~aQQEpA08kSg__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
            like = 1,
            comment = 0
        ),
        PostItem(
            id = "20",
            userId = "33",
            postType = PostItemType.EVENT,
            type = "Staff",
            name = "Kemahasiswaan ITTP",
            date = "50 minutes ago",
            text = "Semangat Pagi!\n" +
                    "Ayo hadiri Talkshow dengan tema \"Bersiap menjadi Generasi Emas dalam Society 5.0 untuk Indonesia Maju. \n" +
                    "Rabu 18 Oktober 2023\n" +
                    "Ruang IOT 101-103",
            media = "https://s3-alpha-sig.figma.com/img/196c/5dbe/f49019777cf148d18a3ad27844b811fa?Expires=1704672000&Signature=Y-K-VrjWjAGkbs4ED3GQFzctJQsZmJugK2z6vDlK0Dkkk2maIC-bs5vZS-wUKc5sMnIQwr1psf9f1Ol2wxpxVcSBswhq-N1PHxN4dpV4JRxN9IuzN6YN1nn2hroNSZ8nayuxkbz12WK811JmwCckbfXF52J3CMn0D4M8HtVyp8E8x3k~RNhokxKcMG7lv2zxR7hE6qjOwyTO49YpfZJytvkYl4SNXAVjXBZ~Y4NMLiKx39cta0-6QjQ9riw6xfrSbP6-7gtsIN0vIK0cNFk1B8rkoLa7a1CXy5iE6KoJy4VEJescHouMjNU6rE-7oGyocHeze3SphYmqyqF-W18yVQ__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
            like = 1,
            comment = 0
        ),
        PostItem(
            id = "21",
            postType = PostItemType.EVENT,
            type = "Staff",
            name = "GDSC IT Telkom Purwo..",
            date = "50 minutes ago",
            text = "[TECH TALK #1] \n" +
                    "Come to join Tech Talk “Prospek Karier menjadi Android Developer di Industri Masa Depan.\n" +
                    "Join us RSVP NOW s.id/GDSCTechTalk1",
            media = "https://s3-alpha-sig.figma.com/img/f82a/e01a/6ab5d93ae33ca31e9f33c8ccc9c2d19c?Expires=1704672000&Signature=FHfK6eM4HIU0ZBjn9giRtHIic-nilHqGxztUVQh68yIfpHgetm5AHIip9WvAObUl~AMQHnZT9LQ1RpvMUhuFZZHAHe3AZy3JEPO9LpURagUZy2XbUHV3XNFYHVYyW3KY3PVhtAfHNLVrcn~D7yGucHuzC6-CezwAjl-rKDtOJbdO-3Oz9zc5pmZpKtoRHXsFBqvS5DYjLzbw0uHykAo2XVX8Qh0iqaUWeqv3qa3j9iFaixgmv41gow~gfRaxuxAO~STArnOGKJu2uF0CUmQUVVnLfM65lb6aQbqjwthyMtiAvU2G1M-HPTwZ0La4ZcER54~qov35M2yuF-5wqomzMQ__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
            like = 1,
            comment = 0
        ),
//        PostItem(
//            id = "22",
//            postType = PostItemType.EVENT,
//            type = "Staff",
//            name = "ESport ITTP",
//            date = "50 minutes ago",
//            text = "HELLO GOOD PEOPLE!\n" +
//                    "Let's join in the fun of ITTP E SPORTS CHAMPIONSHIP SEASON 3!!",
//            media = "https://s3-alpha-sig.figma.com/img/26b1/35c8/056379fca5ff4354ad463ff52b4556ab?Expires=1704672000&Signature=Wy9qYXTVSvfJMQ6qnLi4syFfcgHa5t~S4XiW6i9FAkVFTIPkVFqEcmX10TJgFOe1crSzhagFpJcyeBe65APQHi7sOlx2L55wy0GJ4JuuS-wLWdIXYpM7j~-~wQSaVgLt12jsiOMo2Z~ci~xUSBxrE1Dn074mjaTkLETIShnVp3VpnT6XRWQtiAEiHwzL5lBs-0wmmgM2WV-9Mfx8ZWHTxJXMHhXf5vAH9LxVivNbMjSiT9vD5D1YmFdYA-mJLFbh669RPqsPgkiglH2NUE4PMB1jyI4Gk93tKqrmWYJYpQXL68ZH-J4MT4OMffnE-4wdiSlGbr5cH~r2ZijjsQVz~A__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
//            like = 1,
//            comment = 0
//        ),
        PostItem(
            id = "23",
            userId = "33",
            postType = PostItemType.EVENT,
            type = "Staff",
            name = "Kemahasiswaan ITTP",
            date = "50 minutes ago",
            text = "Ikuti Sosialisasi ini yang dilaksanakan pada Jumat, 10 November 2023, pukul 13.30 - 15.30 WIB di Aula Rektorat Lantai 5",
            media = "https://s3-alpha-sig.figma.com/img/fc12/98f0/764ecdaf86fb9a05be4125504102bb7e?Expires=1704672000&Signature=dA0oZKfb-hwArBaulmbZ1l98vVlnWfsthYBet4Yp5CpumkdbjsRf96weBqOOsPhh8GAJeSL3NtrZLNyVUAp7dq9xJFm5rU1DD4QmfW1ilsVMv7x1qKh~LASzXFstQx23FtUv4b-au7xoKqq8mWOOLD-cOj5qYPgPVBDu6J-YKh8t7w33wkEnxaW8pm72oZuxIvHEeFBB8xK4BhudDF6bwXSISqhXgFZNI0Ki8ZahlYQLjMLgrhB5VKCFUniKCAKpYDkAOukaqcinEtxD3B4DoE7j~Q54RWvX72~xRppnAQ52nGzwf1RNQ38SLUO4xXuHV7myigSM9l8V~N6cxgKeUA__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
            like = 1,
            comment = 0
        ),
        PostItem(
            id = "24",
            userId = "33",
            postType = PostItemType.SCHOLARSHIP,
            type = "Staff",
            name = "Kemahasiswaan ITTP",
            date = "50 minutes ago",
            text = "Halo ITTPizen..\n" +
                    "Pengumuman Evaluasi Beasiswa Internal Semester Gasal 2023/2024\n" +
                    "Link: bit.ly/EvaluasiBeasiswaInternal",
            media = "https://s3-alpha-sig.figma.com/img/32cd/2164/0f98ae55a7c55ff4ff46e900e90a9971?Expires=1704672000&Signature=Xtwa4DEK3L5wkOOVkWV3c~aqH0bMrJHu92CdjkfacDSYvyobIP9lEMwdzzYn19zAVjK0RfjKeMXdJE3SGrP1-VWanuKCT2XyLHQvIeOeKQ9FlB3SUx~KMXNmFW6BIw9v18Zofi3MNpxFNEMPbQX9Jbn~2unliqeCGUkpFeM4F9ShPa4hU0CYn05tYS-IPyg8Dd1~uwklOLbUGxKV-Cpr6VDKSC9CjdMKOhH4~BIsomoykYFQxnLfVPVotstun88Ra0VmSgqmmHYAv3sOBGbVgb6zrfrfZDVf0euypes0GAjTeiGQ9PtJ8FYch4XsMk7SEZN7m7BC~jKT~~7mm3h2AA__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
            like = 1,
            comment = 0
        ),
        PostItem(
            id = "25",
            userId = "33",
            postType = PostItemType.SCHOLARSHIP,
            type = "Staff",
            name = "Kemahasiswaan ITTP",
            date = "50 minutes ago",
            text = "Semangat Pagi!\n" +
                    "Pengumuman Pembukaan Beasiswa KIP Non Biaya hidup. \n" +
                    "Daftar Sekarang!!",
            media = "https://s3-alpha-sig.figma.com/img/1537/dc27/fa28d94bae6585500e91a37d8ece76a2?Expires=1704672000&Signature=dIZ-xunFRtnWU2EEzqp~qbIAZ19oFtdtcIs8n2KQclaiRjOlmLRyO6grvES4CFDO5HKPrvkseAEYmpX8wexfUMgn-dBDvfqcckKkZnE-net5H9czaipMQ499HpfP3K6798xZCPTVzRs9EutiG9J753OcI4k5bEyf6PFZUMwzgZP5vKr1OPwYSUxpDgaphQA2Xgeo6XxVDb3nlraGY3pg3b4HVYEITTu0o2Ij1x~pZkMCMmYURU-usQxh-wKjKmrRTrIS7cPs7Z-h5n-i77QSVWRxwwCt1kCZyFd-5vrTJj6O-JVc0tdWlQSmGbFhVvZu4YyayiH8sMMWi3i1M8zjfA__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
            like = 1,
            comment = 0
        ),
        PostItem(
            id = "26",
            userId = "33",
            postType = PostItemType.SCHOLARSHIP,
            type = "Staff",
            name = "Kemahasiswaan ITTP",
            date = "50 minutes ago",
            text = "Semangat Pagi!\n" +
                    "Pengumuman Penerima Beasiswa KITA & BAIK IT Telkom Purwokerto\n" +
                    "Link: bit.ly/BeasiswaKitaBaik",
            media = "https://s3-alpha-sig.figma.com/img/f963/cf63/0a287ffe24fdaf868be13134db6b1772?Expires=1704672000&Signature=paH-LRQCYrpA9Hny-m9Eqfm-Mom7pqS4~XGWEGNE28OT-Jk1SZdph0yEWFyEjsQIPzPHpuecur6xPpkmmjOhngT3EIY1oJmGU3aLwfudh5aRuEc40LPvNw30ZT6y4IjZy1LiBCCdrJ0ENA0BJJ9urOwtYmKefmHCdNnWicsLGVmXRmuHpVep3UFxuLQ4AcycfZjUs2ultsvxdi3TwQ5k5vWU-dluAxMpOicH4QMqTaq6xVbe9n3hEN5x9XVOfeP68-UujbVFq~95EGe3bc9AGCcrN2ZFOJ~wJsLGxnc1yHnBH77iAWne3p~uhNmSAuPuZTkJjoH17DC3VB6v9W5SIA__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
            like = 1,
            comment = 0
        )
    )


    fun generateAllPost(): List<PostItem> = allPosts

    fun likeOrDislikePost(post: PostItem): PostItem? {
        val selectedPost = allPosts.find { it.id == post.id }  ?: return null
        val isLiked = selectedPost.liked
        val updatedPost = selectedPost.copy(
            liked = isLiked.not(),
            like = if (isLiked) selectedPost.like - 1 else selectedPost.like + 1
        )
        allPosts.replaceAll {
            if (it.id == post.id) updatedPost else it
        }
        return updatedPost
    }

    fun getById(id: String): PostItem? {
        return allPosts.find { it.id == id }
    }

    fun getByUserId(userId: String): List<PostItem> = allPosts.filter { it.userId == userId }

}