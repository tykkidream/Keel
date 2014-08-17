package tykkidream.keel.base.mvc.request;


public interface BaseRequest<ENTITY, ID, PARAMS> extends DoDelete<ID>, DoEdit<ENTITY>, DoNew<ENTITY>, Search<ENTITY, ID, PARAMS> {

}
