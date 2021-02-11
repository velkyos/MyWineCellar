package com.velkyos.mywinecellar.Database.DataEntity;

import androidx.room.Embedded;
import androidx.room.Relation;


public class Wine {
    @Embedded
    public EntityWine _wine;
    @Relation(
            parentColumn = "wine_type",
            entityColumn = "wine_type_id"
    )
    public EntityWineType _type;
    @Relation(
            parentColumn = "wine_sender",
            entityColumn = "wine_sender_id"
    )
    public EntityWineSender _sender;
    @Relation(
            parentColumn = "wine_region",
            entityColumn = "wine_region_id"
    )
    public EntityWineRegion _region;
    @Relation(
            parentColumn = "wine_maker",
            entityColumn = "wine_maker_id"
    )
    public EntityWineMaker _maker;
    @Relation(
            parentColumn = "wine_name",
            entityColumn = "wine_name_id"
    )
    public EntityWineName _name;
}
