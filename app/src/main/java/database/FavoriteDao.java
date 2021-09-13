package database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoriteDao {
    @Insert
    void addData(ShipsList shipsList);


    @Query("SELECT * FROM Shipsdata_TB")
        // @Query("SELECT ship_name FROM Ships_Table2")
        //public List<Ships_model> getFavoriteData();
    List<ShipsList> getAll();


    @Query("SELECT EXISTS (SELECT 1 FROM Shipsdata_TB WHERE ship_name=:shipname)")
    int isFavorite(String shipname);

    @Delete
    void delete(ShipsList shipsList);


    @Query("DELETE FROM Shipsdata_TB")
    void nukeTable();
}
