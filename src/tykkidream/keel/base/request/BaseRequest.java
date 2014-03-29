package tykkidream.keel.base.request;


public interface BaseRequest<ID, DATA, PARAMS, T> extends DoDelete<ID>, DoEdit<DATA>, DoNew<DATA>, Edit<ID>, New<T>, Search<PARAMS>, View<ID> {

}
