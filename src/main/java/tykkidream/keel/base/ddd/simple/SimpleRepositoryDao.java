package tykkidream.keel.base.ddd.simple;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tykkidream.keel.base.Page;
import tykkidream.keel.base.ddd.BaseAggregation;
import tykkidream.keel.base.ddd.BaseID;
import tykkidream.keel.base.ddd.BaseRepository;
import tykkidream.keel.base.tta.BaseDao;

public abstract class SimpleRepositoryDao
	<Entity extends BaseAggregation<EntityId>, EntityId extends BaseID<TrackId>,
		Track, TrackId, TrackDao extends BaseDao<Track, TrackId>>
	implements BaseRepository<Entity, EntityId>{
	
	protected TrackDao baseDao;
	
	protected abstract EntityId convertID(Track track);
	
	protected abstract Track convertEntity(Entity station);

	protected abstract Entity convertTrack(Track track);
	
	/*protected List<Track>convertEntity(List<Entity> info){
		if(null == info || info.size() == 0)
			return null;
		
		List<Track> list = new ArrayList<Track>(info.size());
		for (Iterator<Entity> iterator = info.iterator(); iterator.hasNext();) {
			Entity entity = iterator.next();
			list.add(convertEntity(entity));
		}
		
		return list;
	}*/
	
	protected List<Entity> convertTrack(List<Track> tracks){
		if(null == tracks || tracks.size() == 0)
			return null;
		
		List<Entity> list = new ArrayList<Entity>(tracks.size());
		for (Iterator<Track> iterator = tracks.iterator(); iterator.hasNext();) {
			Track track = iterator.next();
			list.add(convertTrack(track));
		}
		
		return list;
	}

	public TrackDao getTrackDao(){
		return baseDao;
	}

	public void setTrackDao(TrackDao dao){
		this.baseDao = dao;
	}
	
	@Override
	public EntityId nextIdentity() {
		return null;
	}

	@Override
	public int save(Entity t) {
		/*int i = 0;
		if(null == t.getId() || null == t.getId().value()){
			Track track = convertEntity(t);
			i = getTrackDao().insert(track);
			if (i > 0) {
				t.setId(convertID(track));
			}
		} else {
			i = getTrackDao().updateById(convertEntity(t));
		}
		return i;*/
		
		int i = 0;
		if(null == t.id() || null == t.id().value()){
			i =create(t);
		} else {
			i =modiry(t);
		}
		return i;
	}
	
	protected int create(Entity t){
		Track track = convertEntity(t);
		int i = getTrackDao().insert(track);
		if (i > 0) {
			t.id(convertID(track));
		}
		return i;
	}
	
	protected int modiry(Entity t){
		return getTrackDao().updateById(convertEntity(t));
	}

	@Override
	public int save(List<Entity> t) {
		int n = 0;
		Iterator<Entity> iterator = t.iterator();
		while (iterator.hasNext()) {
			Entity t2 = (Entity) iterator.next();
			n += save(t2);
		}
		return n;
	}

	@Override
	public int remove(EntityId t) {
		return getTrackDao().deleteById(t.value());
	}

	@Override
	public int remove(List<EntityId> ts) {
		int n = 0;
		Iterator<EntityId> iterator = ts.iterator();
		while (iterator.hasNext()) {
			EntityId t2 = (EntityId) iterator.next();
			n += remove(t2);
		}
		
		return n;
	}

	@Override
	public Entity find(EntityId y) {
		return convertTrack(getTrackDao().selectById(y.value()));
	}

	@Override
	public List<Entity> find(Page page) {
		List<Track> list = getTrackDao().selectByPage(null, page);
		return convertTrack(list);
	}
	
	@Override
	public List<Entity> find() {
		List<Track> list = getTrackDao().selectByParameters(null);
		return convertTrack(list);
	}

	@Override
	public int size() {
		return getTrackDao().count();
	}
	
	
}
