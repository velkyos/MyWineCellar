package com.velkyos.mywinecellar.Database.DataEntity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(tableName = "wine_list",
        foreignKeys =   {
                                @ForeignKey(entity = EntityWineName.class,
                                        parentColumns = "wine_name_id",
                                        childColumns = "wine_name",
                                        onDelete = ForeignKey.SET_DEFAULT),
                                @ForeignKey(entity = EntityWineType.class,
                                        parentColumns = "wine_type_id",
                                        childColumns = "wine_type",
                                        onDelete = ForeignKey.SET_DEFAULT),
                                @ForeignKey(entity = EntityWineRegion.class,
                                        parentColumns = "wine_region_id",
                                        childColumns = "wine_region",
                                        onDelete = ForeignKey.SET_DEFAULT),
                                @ForeignKey(entity = EntityWineMaker.class,
                                        parentColumns = "wine_maker_id",
                                        childColumns = "wine_maker",
                                        onDelete = ForeignKey.SET_DEFAULT),
                                @ForeignKey(entity = EntityWineSender.class,
                                        parentColumns = "wine_sender_id",
                                        childColumns = "wine_sender",
                                        onDelete = ForeignKey.SET_DEFAULT)
                        },
        indices = {
                                @Index(value = {"wine_maker"}),
                                @Index(value = {"wine_name"}),
                                @Index(value = {"wine_type"}),
                                @Index(value = {"wine_region"}),
                                @Index(value = {"wine_sender"})
                        }
        )
public class EntityWine {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int wine_id;

    @NonNull
    public boolean wine_status;

    @NonNull
    public int wine_number;

    @NonNull
    @ColumnInfo(defaultValue = "1")
    public int wine_name;

    @NonNull
    @ColumnInfo(defaultValue = "1")
    public int wine_type;

    @NonNull
    public int wine_year;

    @NonNull
    @ColumnInfo(defaultValue = "1")
    public int wine_region;

    @NonNull
    @ColumnInfo(defaultValue = "1")
    public int wine_maker;

    @NonNull
    public int wine_price;

    @NonNull
    @ColumnInfo(defaultValue = "1")
    public int wine_sender;

    @NonNull
    public int wine_spoil;

    @NonNull
    public int wine_rating;

    public EntityWine(boolean wine_status, int wine_number, int wine_name, int wine_type, int wine_year, int wine_region, int wine_maker, int wine_price, int wine_sender, int wine_spoil, int wine_rating) {
        this.wine_status = wine_status;
        this.wine_number = wine_number;
        this.wine_name = wine_name;
        this.wine_type = wine_type;
        this.wine_year = wine_year;
        this.wine_region = wine_region;
        this.wine_maker = wine_maker;
        this.wine_price = wine_price;
        this.wine_sender = wine_sender;
        this.wine_spoil = wine_spoil;
        this.wine_rating = wine_rating;
    }

}
//    private int wine_id;
//    private int wine_number;
//    private String wine_name;
//    private String wine_type;
//    private String wine_region;
//    private int wine_year;
//    private String wine_maker;
//    private int wine_price;
//    private String wine_sender;
//    private int wine_spoil;
//    private int rating;
