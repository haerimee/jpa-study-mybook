import ReviewEditor from "../components/ReviewEditor";
import {useLocation} from "react-router-dom";

const New = () => {
    const location = useLocation();
    const data = location.state;

    return (
        <div>
            <ReviewEditor bookInfo={data} />
        </div>

    );
};

export default New;