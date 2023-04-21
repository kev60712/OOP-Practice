package map.object;

import map.GameMap;
import map.state.*;

public class Treasure extends MapObject{

    public enum TreasureType{
        SuperStar,
        Poison,
        AcceleratingPotion,
        HealingPotion,
        DevilFruit,
        KingsRock,
        DokodemoDoor
    }

    private TreasureType type;

    public Treasure(Coord coord, GameMap map, TreasureType type) {
        super("x", coord, map);
        this.type = type;
    }

    public void effect(Role role) {
        System.out.println(String.format("%s got %s", role.getName(), this.type));
        if (this.type.equals(TreasureType.SuperStar)){
            role.updateState(new InvincibleState(role));
        }else if (this.type.equals(TreasureType.Poison)){
            role.updateState(new PoisonedState(role));
        }else if (this.type.equals(TreasureType.AcceleratingPotion)){
            role.updateState(new AcceleratedState(role));
        }else if (this.type.equals(TreasureType.HealingPotion)){
            role.updateState(new HealingState(role));
        }else if (this.type.equals(TreasureType.DevilFruit)){
            role.updateState(new OrderlessState(role));
        }else if (this.type.equals(TreasureType.KingsRock)){
            role.updateState(new StockpileState(role));
        }else if (this.type.equals(TreasureType.DokodemoDoor)){
            role.updateState(new TeleportState(role));
        }
        destroy();
    }

    @Override
    void destroy() {
        this.map.removeObject(this);
    }
}
